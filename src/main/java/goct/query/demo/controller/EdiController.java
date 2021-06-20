package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.EdiDAO;
import goct.query.demo.model.Edi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ListUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EdiController {
    @Autowired
    EdiDAO ediDAO;
    @Autowired
    ActionMapper actionMapper;
    public void recordUserAction(String actionName){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now );
        actionMapper.insertActionSim(actionName,time);
    }

    //重进edi查询
    @RequestMapping(value = "/getFInEdi")
    public ModelAndView getFInEdi(@RequestParam(value = "soNo")String soNo, ModelMap modelMap){


        if(!StringUtils.isEmpty(soNo)){
            try{ediDAO.getDateSource();}catch (Exception e){
                e.printStackTrace();
                System.out.println("数据池连接异常");
            }

            List<Edi> ediList = ediDAO.findFInEdi(soNo);
            modelMap.addAttribute("ediList",ediList);
            modelMap.addAttribute("soNo",soNo);
            //用户行为记录
            String actionName = "重进EDI查询";
            recordUserAction(actionName);
        }
        return new ModelAndView("edi/fInEdi",modelMap);
    }
    //吉出重回edi查询
    @RequestMapping(value = "/getEOutEdi")
    public ModelAndView getEOutEdi(@RequestParam(value = "soNo")String soNo, ModelMap modelMap){
        if(!StringUtils.isEmpty(soNo)){
            try{ediDAO.getDateSource();}catch (Exception e){
                e.printStackTrace();
                System.out.println("数据池连接异常");
            }
            //用户行为记录
            String actionName = "吉出重回EDI查询";
            recordUserAction(actionName);

            List<Edi> ediList = ediDAO.findEOutEdi(soNo);
            modelMap.addAttribute("ediList",ediList);
            modelMap.addAttribute("soNo",soNo);
        }
        return new ModelAndView("edi/eOutEdi",modelMap);
    }
    //重进edi查询
    @RequestMapping(value = "/getFInEdi1")
    public ModelAndView getFInEdi1(ModelMap modelMap){
        return new ModelAndView("edi/fInEdi",modelMap);
    }
    //吉出重回edi查询
    @RequestMapping(value = "/getEOutEdi1")
    public ModelAndView getEOutEdi1( ModelMap modelMap){
        return new ModelAndView("edi/eOutEdi",modelMap);
    }

    //新提吉edi查询（调用中间库存储过程）
    @RequestMapping("/getEoutEdiNew")
    public ModelAndView getEoutEdiNew(@RequestParam(value = "soNo",required = false)String soNo,ModelMap modelMap){
        modelMap.put("soNo",soNo);
        if (!StringUtils.isEmpty(soNo)) {
            List lstEdi = ediDAO.findEOutEdiNew(soNo.toUpperCase());
            if (!ListUtils.isEmpty(lstEdi)){
                modelMap.put("objEdi",lstEdi.get(0));
            }
        }
        return new ModelAndView("edi/eOutEdiNew",modelMap);
    }
}