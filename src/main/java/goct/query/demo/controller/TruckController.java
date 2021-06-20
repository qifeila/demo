package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.TruckDAO;
import goct.query.demo.mapper.sqlServer122.DangerCntrDao;
import goct.query.demo.model.Truck;
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
public class TruckController {
    @Autowired
    TruckDAO truckDAO;
    @Autowired
    DangerCntrDao dangerCntrDao;
    @Autowired(required = false)
    ActionMapper actionMapper;
    public void recordUserAction(String actionName){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now );
        actionMapper.insertActionSim(actionName,time);
    }

    //拖车位置
    @RequestMapping(value = "/getTruckPlace")
    public ModelAndView getTruckPlace(@RequestParam(value = "headNo")String headNo, ModelMap modelMap){
        if(!StringUtils.isEmpty(headNo)){
            //用户行为记录
            String actionName = "当前作业查询";
            recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findTruckPlace(headNo);
            for (Truck truck:truckList) {
                //根据箱号查找122危险品信息
                if(!StringUtils.isEmpty(truck.getCargoType()) && truck.getCargoType().equals("DG") &&truck.getGjobType().equals("提柜")){
                    //DG柜查询122
                    Truck dangerTruck = dangerCntrDao.getByCntrNo(truck.getCntrNo()==null?"":truck.getCntrNo());
                    if(dangerTruck!=null){
                        truck.setCategory(StringUtils.isEmpty(dangerTruck.getCategory())?"":dangerTruck.getCategory());
                        truck.setUnNo(StringUtils.isEmpty(dangerTruck.getUnNo())?"":dangerTruck.getUnNo());
                        truck.setDangerName(StringUtils.isEmpty(dangerTruck.getDangerName())?"":dangerTruck.getDangerName());
                        truck.setDangerWeight(StringUtils.isEmpty(dangerTruck.getDangerWeight())?"":dangerTruck.getDangerWeight());
                    }
                }
            }
            modelMap.addAttribute("truckList",truckList);
            modelMap.addAttribute("headNo",headNo);
        }
        return new ModelAndView("truck/truckPlace",modelMap);
    }
    //拖车位置
    @RequestMapping(value = "/getTruckPlace1")
    public ModelAndView getTruckPlace(ModelMap modelMap){
        return new ModelAndView("truck/truckPlace",modelMap);
    }

    //拖车周转时间
    @RequestMapping(value = "/getTruckTime")
    public ModelAndView getTruckTime(@RequestParam(value = "headNo")String headNo, ModelMap modelMap){
        if(!StringUtils.isEmpty(headNo)){
            //用户行为记录
            String actionName = "拖车周转时间";
           recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findTruckTime(headNo);
            modelMap.addAttribute("truckList",truckList);}
        return new ModelAndView("truck/truckTime",modelMap);
    }
    //拖车周转时间
    @RequestMapping(value = "/getTruckTime1")
    public ModelAndView getTruckTime(ModelMap modelMap){
        return new ModelAndView("truck/truckTime",modelMap);
    }
    //柜号对应信息
    @RequestMapping(value = "/getTruckContainer")
    public ModelAndView getTruckContainer(@RequestParam(value = "container")String container, ModelMap modelMap){
        if(!StringUtils.isEmpty(container)){
            //用户行为记录
            String actionName = "预约信息查询";
          recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findTruckContainer(container);
            modelMap.addAttribute("truckList",truckList);}
        return new ModelAndView("truck/truckContainer",modelMap);
    }
    //柜号对应信息
    @RequestMapping(value = "/getTruckContainer1")
    public ModelAndView getTruckContainer(ModelMap modelMap){
        return new ModelAndView("truck/truckContainer",modelMap);
    }

    //基于提单号查拖车历史进出记录
    @RequestMapping(value = "/getTruckBLNO")
    public ModelAndView getTruckBLNO(@RequestParam(value = "BLNO")String BLNO, ModelMap modelMap){
        if(!StringUtils.isEmpty(BLNO)){
            //用户行为记录
            String actionName = "提单号查拖车历作业";
          recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findTruckBLNO(BLNO);
            modelMap.addAttribute("truckList",truckList);}
        return new ModelAndView("truck/truckBLNO",modelMap);
    }
    //基于提单号查拖车历史进出记录
    @RequestMapping(value = "/getTruckBLNO1")
    public ModelAndView getTruckBLNO(ModelMap modelMap){
        return new ModelAndView("truck/truckBLNO",modelMap);
    }

    //查询三个月内集装箱的进出闸记录
    @RequestMapping(value = "/getContainerHis")
    public ModelAndView getContainerHis(@RequestParam(value = "container")String container, ModelMap modelMap){
        if(!StringUtils.isEmpty(container)){
            //用户行为记录
            String actionName = "集装箱进出闸记录";
           recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findContainerHis(container);
            modelMap.addAttribute("truckList",truckList);
            modelMap.addAttribute("container",container);
        }
        return new ModelAndView("truck/containerHis",modelMap);
    }
    //查询三个月内集装箱的进出闸记录
    @RequestMapping(value = "/getContainerHis1")
    public ModelAndView getContainerHis(ModelMap modelMap){
        return new ModelAndView("truck/containerHis",modelMap);
    }
    //基于月份查拖车历史作业记录
    @RequestMapping(value = "/getTruckHis")
    public ModelAndView getTruckHis(@RequestParam(value = "time")String time,
                                    @RequestParam(value = "headNo")String headNo, ModelMap modelMap){
        //年月车牌不为空判断
        if(!StringUtils.isEmpty(headNo)&&!StringUtils.isEmpty(time)){
            //用户行为记录
            String actionName = "拖车历史作业";
            recordUserAction(actionName);
            List<Truck> truckList = truckDAO.findTruckHis(headNo, time);
            modelMap.addAttribute("truckList",truckList);}
        return new ModelAndView("truck/truckHis",modelMap);
    }
    //基于月份查拖车历史作业记录
    @RequestMapping(value = "/getTruckHis1")
    public ModelAndView getTruckHis(ModelMap modelMap){
        return new ModelAndView("truck/truckHis",modelMap);
    }
    //查询拖车是否备案

    @RequestMapping(value = "/getTruckData")
    public ModelAndView getTruckData(@RequestParam(value = "headNo")String headNo, ModelMap modelMap){
        //年月车牌不为空判断
        if(!StringUtils.isEmpty(headNo)){
            //用户行为记录
            String actionName = "备案资料查询";
            recordUserAction(actionName);
            try{truckDAO.getDateSource();}catch (Exception e){
                e.printStackTrace();
                System.out.println("数据池连接异常");
            }
           // TruckDAO truckDAO1 = new TruckDAO();

            List<Truck> truckList = truckDAO.findTruckData(headNo);
            modelMap.addAttribute("truckList",truckList);}
        return new ModelAndView("truck/truckData",modelMap);
    }
    //查询拖车是否备案
    @RequestMapping(value = "/getTruckData1")
    public ModelAndView getTruckData(ModelMap modelMap){
        return new ModelAndView("truck/truckData",modelMap);
    }
    //查询拖车超长指令
    @RequestMapping(value = "/truckWaitLong")
    public ModelAndView getTruckWaitLong( ModelMap modelMap){
        List<Truck> truckList = truckDAO.findTruckLong();
        modelMap.addAttribute("truckList",truckList);
        return new ModelAndView("truck/truckWaitLong",modelMap);
    }

}
