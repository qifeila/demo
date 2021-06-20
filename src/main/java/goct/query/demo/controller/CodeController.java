package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.CodeDAO;
import goct.query.demo.model.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CodeController {
    @Autowired
    CodeDAO codeDAO;
    @Autowired
    ActionMapper actionMapper;
    public void recordUserAction(String actionName){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now );
        actionMapper.insertActionSim(actionName,time);
    }
    // 1. 箱属代码 输入条件：中文名字
    @RequestMapping(value = "/getPtnrCode")
    public ModelAndView getPtnrCode(@RequestParam(value = "engSnm" ,required = false )String engSnm, ModelMap modelMap){
        if(!StringUtils.isEmpty(engSnm)){
            List<Code> codeList = codeDAO.findPtnrCode(engSnm);
            modelMap.addAttribute("codeList",codeList);
            modelMap.addAttribute("engSnm",engSnm);
            //用户行为记录
            String actionName = "箱属代码查询";
            recordUserAction(actionName);
        }
        return new ModelAndView("code/ptnrCode",modelMap);
    }
    //2. 港口代码 输入条件：国家代码或港口代码或港口名称
    @RequestMapping(value = "/getPortCode")
    public ModelAndView getPortCode(@RequestParam(value = "portName" ,required = false )String portName, ModelMap modelMap){
        if(!StringUtils.isEmpty(portName)){
            List<Code> codeList = codeDAO.portCode(portName);
            modelMap.addAttribute("codeList",codeList);
            modelMap.addAttribute("portName",portName);
            //用户行为记录
            String actionName = "港口代码查询";
            recordUserAction(actionName);
        }
        return new ModelAndView("code/portCode",modelMap);
    }
    // 3. . 用箱规则 输入条件：英文代码箱属
    @RequestMapping(value = "/getCntrRule")
    public ModelAndView getCntrRule(@RequestParam(value = "ptnrCode" ,required = false )String ptnrCode, ModelMap modelMap){
        if(!StringUtils.isEmpty(ptnrCode)){
            List<Code> codeList = codeDAO.cntrRule(ptnrCode);
            modelMap.addAttribute("codeList",codeList);
            modelMap.addAttribute("ptnrCode",ptnrCode);
            //用户行为记录
            String actionName = "用箱规则查询";
            recordUserAction(actionName);
        }
        return new ModelAndView("code/cntrRule",modelMap);
    }
    // 4. 危险品代码 输入条件： 危险品代码或类别
    @RequestMapping(value = "/getDangerCode")
    public ModelAndView getDangerCode(@RequestParam(value = "unno" ,required = false )String unno, ModelMap modelMap){
        if(!StringUtils.isEmpty(unno)){
            List<Code> codeList = codeDAO.dangerCode(unno);
            modelMap.addAttribute("codeList",codeList);
            modelMap.addAttribute("unno",unno);
            String actionName = "危险品代码查询";
            recordUserAction(actionName);
        }
        return new ModelAndView("code/dangerCode",modelMap);
    }
}
