package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.BerthDAO;

import goct.query.demo.model.Berth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

@Controller
public class BerthController {

    @Autowired
    BerthDAO berthDAO;

    @Autowired
    ActionMapper actionMapper;

    public void recordUserAction(String actionName){
       //
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now );


       // actionMapper.insertActionSim(actionName,time);
    }



    @RequestMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    //未来十天船期
    @RequestMapping(value = "/getBerthPlan")
    public ModelAndView getBerthPlan(ModelMap modelMap){

        List<Berth> berthList = berthDAO.findShipAll();
        List<Berth> goctList = berthDAO.findShipTerminal(berthList,"GOCT");

        //用户行为记录
        String actionName = "班轮集港计划";
        recordUserAction(actionName);

        String status = "1";
        modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);

        return new ModelAndView("berth/shipPlan",modelMap);
    }
    @RequestMapping(value = "/getBerthPlan2")
    public ModelAndView getBerthPlan2(ModelMap modelMap){
        // BerthDAO berthDAO = new BerthDAO();
        // BerthTem berthDAO = new BerthTem();
        List<Berth> berthList = berthDAO.findShipAll();
       // List<Berth> goctList = berthDAO.findShipTerminal(berthList,"GOCT");
        List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");
        String status = "2";
        //用户行为记录
        String actionName = "班轮集港计划";
        recordUserAction(actionName);
       // modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        modelMap.addAttribute("berthList",nctList);
        return new ModelAndView("berth/shipPlan",modelMap);
    }
    //在港大船
    @RequestMapping(value = "/getShipInPort")
    public ModelAndView getShipInPort(ModelMap modelMap){
        // BerthDAO berthDAO = new BerthDAO();
        // BerthTem berthDAO = new BerthTem();
        List<Berth> berthList = berthDAO.findShipInPort();
        List<Berth> goctList = berthDAO.findShipTerminal(berthList,"GOCT");
        String status = "1";
        modelMap.addAttribute("status",status);
        modelMap.addAttribute("berthList",goctList);
        //用户行为记录
        String actionName = "当前在港班轮";
        recordUserAction(actionName);
        return new ModelAndView("berth/shipInPort",modelMap);
    }
    @RequestMapping(value = "/getShipInPort2")
    public ModelAndView getShipInPort2(ModelMap modelMap){
        // BerthDAO berthDAO = new BerthDAO();
        // BerthTem berthDAO = new BerthTem();
        List<Berth> berthList = berthDAO.findShipInPort();
        List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");
        String status = "2";
        //用户行为记录
        String actionName = "当前在港班轮";
        recordUserAction(actionName);
        // modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        modelMap.addAttribute("berthList",nctList);
        return new ModelAndView("berth/shipInPort",modelMap);
    }
    //在港驳船
    @RequestMapping(value = "/getBargeInPort")
    public ModelAndView getBargeInPort(ModelMap modelMap){
        List<Berth> berthList = berthDAO.findBargeInPort();
        List<Berth> goctList = berthDAO.findShipTerminal(berthList,"GOCT");
        //   List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");
        //用户行为记录
        String actionName = "当前在港驳船";
        recordUserAction(actionName);
        String status = "1";
        modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        return new ModelAndView("berth/bargeInPort",modelMap);
    }
    @RequestMapping(value = "/getBargeInPort2")
    public ModelAndView getBargeInPort2(ModelMap modelMap){
        List<Berth> berthList = berthDAO.findBargeInPort();
        modelMap.addAttribute("berthList",berthList);
        List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");

        //用户行为记录
        String actionName = "当前在港驳船";
        recordUserAction(actionName);
        String status = "2";
        // modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        modelMap.addAttribute("berthList",nctList);

        return new ModelAndView("berth/bargeInPort",modelMap);
    }
    //离港大船
    @RequestMapping(value = "/getShipDepartPort")
    public ModelAndView getShipDepartPort(ModelMap modelMap){
        List<Berth> berthList = berthDAO.findShipDepartPort();
        List<Berth> goctList = berthDAO.findShipTerminal(berthList,"GOCT");
        //   List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");
        //用户行为记录
        String actionName = "当前离港班轮";
        recordUserAction(actionName);
        String status = "1";
        modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        return new ModelAndView("berth/shipDepartPort",modelMap);
    }
    @RequestMapping(value = "/getShipDepartPort2")
    public ModelAndView getShipDepartPort2(ModelMap modelMap){
        List<Berth> berthList = berthDAO.findShipDepartPort();
        List<Berth> nctList = berthDAO.findShipTerminal(berthList,"NCT");
        String status = "2";
        //用户行为记录
        String actionName = "当前离港班轮";
        recordUserAction(actionName);
        // modelMap.addAttribute("berthList",goctList);
        modelMap.addAttribute("status",status);
        modelMap.addAttribute("berthList",nctList);
        return new ModelAndView("berth/shipDepartPort",modelMap);
    }




}
