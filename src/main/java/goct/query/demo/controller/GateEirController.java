package goct.query.demo.controller;

import goct.query.demo.mapper.oracleGoct.CoparnMapper;
import goct.query.demo.mapper.oracleGoct.GateEirMapper;
import goct.query.demo.mapper.oracleGoct.PreGateMapper;
import goct.query.demo.mapper.sqlServer122.UserRoleDAO;
import goct.query.demo.model.Coparn;
import goct.query.demo.model.GateEir;
import goct.query.demo.model.PreGate;
import goct.query.demo.model.UserInfo;
import goct.query.demo.util.EportalLogin;
import goct.query.demo.util.Result;
import goct.query.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ListUtils;

import java.util.*;

@Controller
public class GateEirController {
    @Autowired
    GateEirMapper geteEirMapper;
    @Autowired
    UserRoleDAO userRoleDAO;
    @Autowired
    CoparnMapper coparnMapper;
    @Autowired
    PreGateMapper preGateMapper;

    //根据箱号查找箱信息
    @RequestMapping(value = "/gateEirQuery")
    public ModelAndView gateEirQuery(@RequestParam(value = "cntrNo", required = false) String cntrNo,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "webFlag", required = false) String webFlag,
                                     ModelMap modelMap) {
        modelMap.put("phone", phone);
        modelMap.put("cntrNo", cntrNo);
        modelMap.put("webFlag", webFlag);
        String tips = "";
        //验证手机号参数
        if (StringUtils.isEmpty(phone)) {
            tips = "用户参数错误！";
            modelMap.put("tips", tips);
            return new ModelAndView("driver/exception", modelMap);
        }
        //校验phone权限
        UserInfo userInfo = userRoleDAO.findPassUser(phone);
        if (userInfo == null) {
            tips = "该用户无权限，请联系系统管理员！";
            modelMap.put("tips", tips);
            return new ModelAndView("driver/exception", modelMap);
        }
        modelMap.put("user", userInfo);
        //通过校验，查找箱
        List<GateEir> lstGateEir;
        if (!StringUtils.isEmpty(cntrNo)) {
            //gate表查询
            lstGateEir = geteEirMapper.findByCntr(cntrNo.toUpperCase());
            if (!ListUtils.isEmpty(lstGateEir)) {
                Collections.sort(lstGateEir, Comparator.comparing(GateEir::getUpdate_time));
                modelMap.put("gateEir", lstGateEir.get(lstGateEir.size() - 1));
            } else {
                tips = "该箱不在二期出闸，但您仍可录入以下修箱信息";
            }
            //coparn查询
            Coparn coparn;
            List<Coparn> lstCoparn = coparnMapper.findByCntr(cntrNo.toUpperCase());
            //Coparn coparn = !ListUtils.isEmpty(lstCoparn)?lstCoparn.get(0):new Coparn();
            if (ListUtils.isEmpty(lstCoparn)) {
                //coparn表没数据，查找pre_gate表
                tips = "该箱还未办单，但您可提前录入该箱信息，办单后系统将自动录入";
                List<PreGate> lstPreGate = preGateMapper.findByCntr(cntrNo.toUpperCase());
                coparn = new Coparn();
                if (!ListUtils.isEmpty(lstPreGate)) {
                    //临时表有数据，赋值到coparn
                    coparn.setCntr_no(lstPreGate.get(0).getCntr_no());
                    coparn.setRepair_remark(lstPreGate.get(0).getRepair_remark());
                    coparn.setRepair_state(lstPreGate.get(0).getCntr_repair_state());
                    coparn.setRepair_time(lstPreGate.get(0).getRepair_time());
                    coparn.setRepairer(lstPreGate.get(0).getRepairer());
                }
            } else {
                coparn = lstCoparn.get(0);
            }
            modelMap.put("coparn", coparn);
            if (StringUtils.isEmpty(cntrNo))  //首次进入无提示
                tips = null;
            modelMap.put("tips", tips);
        }
        return new ModelAndView("gateEir/gateEirList", modelMap);


    }

    @RequestMapping(value = "/coparnUpdate")
    @ResponseBody
    public Result coparnUpdate(@RequestParam(value = "phone", required = false) String phone,
                               @RequestParam(value = "cntrNo", required = false) String cntrNo,
                               @RequestParam(value = "repairState") String repairState,
                               @RequestParam(value = "repairRemark") String repairRemark) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(cntrNo) || StringUtils.isEmpty(repairState))
            return ResultUtil.error(2, "参数错误！");
        UserInfo userInfo = userRoleDAO.findPassUser(phone);
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()))
            return ResultUtil.error(2, "该用户信息不全或无相应权限，请联系系统管理员！");
        List<Coparn> lstCoparn = coparnMapper.findByCntr(cntrNo.toUpperCase());
        Coparn coparn;
        String extractFlag = ""; //缓存表插入是否存在COPARN数据标识
        String remarkerInfo = userInfo.getName() + "，" + userInfo.getAccount();
        Date dateNow = new Date();
        //1.查到有coparn数据,更新coparn，根据情况插入goct_pre_gate表数据
        if (!ListUtils.isEmpty(lstCoparn)) {
            coparn = lstCoparn.get(0);
            extractFlag = "Y";
            if (!StringUtils.isEmpty(coparn.getRepairer()) && !coparn.getRepairer().contains(userInfo.getName())) {
                return ResultUtil.error(2, "该箱信息已被人修改，不允许当前用户变更！");
            }
            //更新coparn
            try {
                coparnMapper.updateCopar(coparn.getOdr_no(), repairState, remarkerInfo, repairRemark, dateNow);
            } catch (Exception e) {
                return ResultUtil.error(0, "保存失败！501");
            }
        } else {
            //2.未查到coparn数据，插入goct_pre_gate表数据
            extractFlag = "N";
        }
        //最后插入goct_pre_gate表数据
        PreGate preGate = new PreGate();
        preGate.setCntr_no(cntrNo.toUpperCase());
        preGate.setExtract_flag(extractFlag);
        preGate.setRepair_remark(repairRemark);
        preGate.setCntr_repair_state(repairState);
        preGate.setRepairer(remarkerInfo);
        preGate.setRepair_time(dateNow);
        try {
            preGateMapper.insertPreGate(preGate);
        } catch (Exception e) {
            return ResultUtil.error(0, "保存失败!502");
        }
        return ResultUtil.success("保存成功！");
    }

    //外修箱用户登录界面
    @RequestMapping(value = "/loginUser")
    public ModelAndView loginUser(ModelMap modelMap) {
        return new ModelAndView("gateEir/loginUser", modelMap);
    }

    //外修箱场用户登录
    @RequestMapping(value = "/repairerLogin")
    @ResponseBody
    public Result repairerLogin(@RequestParam(value = "account", required = false) String account,
                                @RequestParam(value = "password", required = false) String password) {
        //调用eportal接口验证用户登录
        boolean boolPass = EportalLogin.sendPostUrl("http://10.1.0.26:80/Account/ValidateEportalUser/",
                "UserAccount=" + account + "&Password=" + password);
        if (!boolPass) {
            return ResultUtil.error(2, "用户名或密码错误！");
        }
        //验证权限
        UserInfo userInfo = userRoleDAO.findByAccout(account);
        if (userInfo == null || StringUtils.isEmpty(userInfo.getName()) || StringUtils.isEmpty(userInfo.getRemark()))
            return ResultUtil.error(2, "该用户信息不全或无相应权限，请联系系统管理员！");
        //通过验证
        return ResultUtil.success(userInfo);
    }

}
