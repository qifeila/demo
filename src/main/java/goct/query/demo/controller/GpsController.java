package goct.query.demo.controller;

import goct.query.demo.mapper.oracleGis.GpsMapper;
import goct.query.demo.model.GpsDeviceLog;
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

import java.util.*;

@Controller
public class GpsController {
    @Autowired
    GpsMapper gpsMapper;


    @RequestMapping(value = "/gpsDeviceQuery")
    public ModelAndView internalTruckQty(ModelMap modelMap) {
        return new ModelAndView("gis/gpsDeviceQuery", modelMap);
    }

    //GPS设备ajax
    @RequestMapping(value = "/getGpsDeviceData")
    @ResponseBody
    public Result getInternalTruckQty(@RequestParam(value = "deviceName", required = false) String deviceName,
                                      @RequestParam(value = "deviceIp", required = false) String deviceIp) {

        if (StringUtils.isEmpty(deviceIp) &&StringUtils.isEmpty(deviceName)){
            return  ResultUtil.error(0,"请输入设备号或IP");
        }
        List<GpsDeviceLog> list;
            try {
                list = gpsMapper.findByCondition(deviceIp,deviceName);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(0, "数据库连接异常！");
            }
        return ResultUtil.success(list);
    }

}