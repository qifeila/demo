package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.DriverDAO;
import goct.query.demo.model.Driver;
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

import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class DriverController {
    @Autowired
    DriverDAO driverDAO;
    @Autowired
    ActionMapper actionMapper;

    public void recordUserAction(String actionName) {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format(now);
        actionMapper.insertActionSim(actionName, time);
    }

    // 1. 二期员工查计件
    @RequestMapping(value = "/driver")
    public ModelAndView getDriver(@RequestParam(value = "weixinid", required = false) String weixinId, ModelMap modelMap) {
        if (StringUtils.isEmpty(weixinId)) {
            // modelMap.addAttribute("weixinId",weixinId);
            String tips = "你沒有权限使用此功能！";
            modelMap.addAttribute("tips", tips);
            return new ModelAndView("driver/exception", modelMap);
        } else
            modelMap.addAttribute("weixinId", weixinId);
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String sMonth = sim.format(date);
        modelMap.put("sMonth", sMonth);

        return new ModelAndView("driver/driverQty", modelMap);
    }

    // 1. 二期员工查计件
    @RequestMapping(value = "/driverQty")
    public ModelAndView getDriverQty(@RequestParam(value = "weixinid") String weixinId,
                                     @RequestParam(value = "month") String month, ModelMap modelMap) {


        List<Driver> driverList1 = new ArrayList<>();
        List<Driver> driverList2 = new ArrayList<>();
        List<Driver> driverList3 = new ArrayList<>();
        List<Driver> driverList4 = new ArrayList<>();
        int catosIdSumQty = 0;
        int catosId2SumQty = 0;
        int catosId3SumQty = 0;
        int catosId4SumQty = 0;
        int sumQty = 0;


        //用户行为记录
        String actionName = "司机查计件";
        recordUserAction(actionName);


        if (!StringUtils.isEmpty(month)) {
            if (!StringUtils.isEmpty(weixinId)) {
                try {
                    driverDAO.getDateSource();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("数据池连接异常");
                }
                String employeeId = driverDAO.getEmployeeId(weixinId);
                if (!StringUtils.isEmpty(employeeId)) {
                    Driver driver = driverDAO.findDriver(employeeId).get(0);
                    driverList1 = driverDAO.findDriverQty(driver.getCatosId(), month);
                    String driverName = driver.getDriverName();
                    String catosId = driver.getCatosId();
                    modelMap.addAttribute("driverName", driverName);
                    Collections.sort(driverList1, Comparator.comparing(Driver::getDay));
                    int iSum10=0,iSum20=0,iSum40=0,iSum45=0,iSumSpe=0,iSumDanger=0;
                    for (Driver driver1 : driverList1) {
                        catosIdSumQty += driver1.getQty();
                        iSum10 += StringUtils.isEmpty(driver1.getCntr10())?0:Integer.parseInt(driver1.getCntr10());
                        iSum20 += StringUtils.isEmpty(driver1.getCntr20())?0:Integer.parseInt(driver1.getCntr20());
                        iSum40 += StringUtils.isEmpty(driver1.getCntr40())?0:Integer.parseInt(driver1.getCntr40());
                        iSum40 += StringUtils.isEmpty(driver1.getCntr45())?0:Integer.parseInt(driver1.getCntr45());
                        iSumSpe += StringUtils.isEmpty(driver1.getCntrSpe())?0:Integer.parseInt(driver1.getCntrSpe());
                        iSumDanger += StringUtils.isEmpty(driver1.getDangerCntr())?0:Integer.parseInt(driver1.getDangerCntr());
                    }
                    if(!ListUtils.isEmpty(driverList1)){
                        Driver driverVirtual = new Driver();
                        driverVirtual.setDay("月总计：");
                        driverVirtual.setCntr10(Integer.toString(iSum10));
                        driverVirtual.setCntr20(Integer.toString(iSum20));
                        driverVirtual.setCntr40(Integer.toString(iSum40));
                        driverVirtual.setCntr45(Integer.toString(iSum45));
                        driverVirtual.setCntrSpe(Integer.toString(iSumSpe));
                        driverVirtual.setDangerCntr(Integer.toString(iSumDanger));
                        driverVirtual.setQty(catosIdSumQty);
                        driverList1.add(driverVirtual);
                    }
                    modelMap.addAttribute("catosId", catosId);
                    modelMap.addAttribute("driverList1", driverList1);
                    if (!StringUtils.isEmpty(driver.getCatosId2())) {
                        driverList2 = driverDAO.findDriverQty(driver.getCatosId2(), month);
                        String catosId2 = driver.getCatosId2();
                        Collections.sort(driverList2, Comparator.comparing(Driver::getDay));
                        iSum10=iSum20=iSum40=iSum45=iSumSpe=iSumDanger=0;
                        for (Driver driver1 : driverList2) {
                            catosId2SumQty += driver1.getQty();
                            iSum10 += StringUtils.isEmpty(driver1.getCntr10())?0:Integer.parseInt(driver1.getCntr10());
                            iSum20 += StringUtils.isEmpty(driver1.getCntr20())?0:Integer.parseInt(driver1.getCntr20());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr40())?0:Integer.parseInt(driver1.getCntr40());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr45())?0:Integer.parseInt(driver1.getCntr45());
                            iSumSpe += StringUtils.isEmpty(driver1.getCntrSpe())?0:Integer.parseInt(driver1.getCntrSpe());
                            iSumDanger += StringUtils.isEmpty(driver1.getDangerCntr())?0:Integer.parseInt(driver1.getDangerCntr());
                        }
                        if(!ListUtils.isEmpty(driverList2)){
                            Driver driverVirtual = new Driver();
                            driverVirtual.setDay("月总计：");
                            driverVirtual.setCntr10(Integer.toString(iSum10));
                            driverVirtual.setCntr20(Integer.toString(iSum20));
                            driverVirtual.setCntr40(Integer.toString(iSum40));
                            driverVirtual.setCntr45(Integer.toString(iSum45));
                            driverVirtual.setCntrSpe(Integer.toString(iSumSpe));
                            driverVirtual.setDangerCntr(Integer.toString(iSumDanger));
                            driverVirtual.setQty(catosId2SumQty);
                            driverList2.add(driverVirtual);
                        }
                        modelMap.addAttribute("catosId2", catosId2);
                        modelMap.addAttribute("driverList2", driverList2);
                    }
                    if (!StringUtils.isEmpty(driver.getCatosId3())) {
                        driverList3 = driverDAO.findDriverQty(driver.getCatosId3(), month);
                        String catosId3 = driver.getCatosId3();
                        Collections.sort(driverList3, Comparator.comparing(Driver::getDay));
                        iSum10=iSum20=iSum40=iSum45=iSumSpe=iSumDanger=0;
                        for (Driver driver1 : driverList3) {
                            catosId3SumQty += driver1.getQty();
                            iSum10 += StringUtils.isEmpty(driver1.getCntr10())?0:Integer.parseInt(driver1.getCntr10());
                            iSum20 += StringUtils.isEmpty(driver1.getCntr20())?0:Integer.parseInt(driver1.getCntr20());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr40())?0:Integer.parseInt(driver1.getCntr40());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr45())?0:Integer.parseInt(driver1.getCntr45());
                            iSumSpe += StringUtils.isEmpty(driver1.getCntrSpe())?0:Integer.parseInt(driver1.getCntrSpe());
                            iSumDanger += StringUtils.isEmpty(driver1.getDangerCntr())?0:Integer.parseInt(driver1.getDangerCntr());
                        }
                        if(!ListUtils.isEmpty(driverList3)){
                            Driver driverVirtual = new Driver();
                            driverVirtual.setDay("月总计：");
                            driverVirtual.setCntr10(Integer.toString(iSum10));
                            driverVirtual.setCntr20(Integer.toString(iSum20));
                            driverVirtual.setCntr40(Integer.toString(iSum40));
                            driverVirtual.setCntr45(Integer.toString(iSum45));
                            driverVirtual.setCntrSpe(Integer.toString(iSumSpe));
                            driverVirtual.setDangerCntr(Integer.toString(iSumDanger));
                            driverVirtual.setQty(catosId3SumQty);
                            driverList3.add(driverVirtual);
                        }
                        modelMap.addAttribute("catosId3", catosId3);
                        modelMap.addAttribute("driverList3", driverList3);
                    }
                    if (!StringUtils.isEmpty(driver.getCatosId4())) {
                        driverList4 = driverDAO.findDriverQty(driver.getCatosId4(), month);
                        Collections.sort(driverList4, Comparator.comparing(Driver::getDay));
                        String catosId4 = driver.getCatosId4();
                        iSum10=iSum20=iSum40=iSum45=iSumSpe=iSumDanger=0;
                        for (Driver driver1 : driverList4) {
                            catosId4SumQty += driver1.getQty();
                            iSum10 += StringUtils.isEmpty(driver1.getCntr10())?0:Integer.parseInt(driver1.getCntr10());
                            iSum20 += StringUtils.isEmpty(driver1.getCntr20())?0:Integer.parseInt(driver1.getCntr20());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr40())?0:Integer.parseInt(driver1.getCntr40());
                            iSum40 += StringUtils.isEmpty(driver1.getCntr45())?0:Integer.parseInt(driver1.getCntr45());
                            iSumSpe += StringUtils.isEmpty(driver1.getCntrSpe())?0:Integer.parseInt(driver1.getCntrSpe());
                            iSumDanger += StringUtils.isEmpty(driver1.getDangerCntr())?0:Integer.parseInt(driver1.getDangerCntr());
                        }
                        if(!ListUtils.isEmpty(driverList4)){
                            Driver driverVirtual = new Driver();
                            driverVirtual.setDay("月总计：");
                            driverVirtual.setCntr10(Integer.toString(iSum10));
                            driverVirtual.setCntr20(Integer.toString(iSum20));
                            driverVirtual.setCntr40(Integer.toString(iSum40));
                            driverVirtual.setCntr45(Integer.toString(iSum45));
                            driverVirtual.setCntrSpe(Integer.toString(iSumSpe));
                            driverVirtual.setDangerCntr(Integer.toString(iSumDanger));
                            driverVirtual.setQty(catosId4SumQty);
                            driverList4.add(driverVirtual);
                        }
                        modelMap.addAttribute("catosId4", catosId4);
                        modelMap.addAttribute("driverList4", driverList4);
                    }

                } else {
                    String tip = "该weixinId找不到用户";
                    modelMap.addAttribute("tip", tip);
                }


            }
        }
        sumQty = catosIdSumQty + catosId2SumQty + catosId3SumQty + catosId4SumQty;
        modelMap.addAttribute("catosIdSumQty", catosIdSumQty);
        modelMap.addAttribute("catosId2SumQty", catosId2SumQty);
        modelMap.addAttribute("catosId3SumQty", catosId3SumQty);
        modelMap.addAttribute("catosId4SumQty", catosId4SumQty);
        modelMap.addAttribute("sumQty", sumQty);
        modelMap.addAttribute("month", month);

        modelMap.addAttribute("weixinId", weixinId);
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String sMonth = month == null ? sim.format(date) : month;
        modelMap.put("sMonth", sMonth);
        return new ModelAndView("driver/driverQty", modelMap);
    }

    // 2. 中控计件
    @RequestMapping(value = "/worker")
    public ModelAndView getWorker(@RequestParam(value = "weixinid", required = false) String weixinId, ModelMap modelMap) {
        //  System.out.println(weixinId);
        //weixinId = "oFWWcuNuRK70hGqkc0Uu1ujyuNgM";
        if (StringUtils.isEmpty(weixinId)) {
            String tips = "你沒有权限使用此功能！";

        }
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String sMonth = sim.format(date);
        modelMap.put("month", sMonth);
        modelMap.addAttribute("weixinId", weixinId);

        return new ModelAndView("driver/workerQty", modelMap);
    }

    // 2. 中控计件
    @RequestMapping(value = "/workerQty")
    public ModelAndView getWorkerQty(@RequestParam(value = "weixinid") String weixinId,
                                     @RequestParam(value = "month") String month, ModelMap modelMap) {

        List<Driver> driverList1 = new ArrayList<>();

        if (!StringUtils.isEmpty(month)) {
            //用户行为记录
            String actionName = "中控查计件";
            recordUserAction(actionName);
            if (!StringUtils.isEmpty(weixinId)) {
                try {
                    driverDAO.getDateSource();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("数据池连接异常");
                }
                String employeeId = driverDAO.getEmployeeId(weixinId);
                if (!StringUtils.isEmpty(employeeId)) {
                    Driver driver = driverDAO.findDriver(employeeId).get(0);
                    driverList1 = driverDAO.findWorkerQty(driver.getCatosId(), month);
                    String driverName = driver.getDriverName();
                    String catosId = driver.getCatosId();
                    int sum = 0;
                    for (Driver driver1 : driverList1) {
                        sum += driver1.getQty();
                    }
                    modelMap.addAttribute("sum", sum);
                    modelMap.addAttribute("driverName", driverName);
                    modelMap.addAttribute("catosId", catosId);
                    modelMap.addAttribute("driverList1", driverList1);

                } else {
                    String tip = "该weixinId找不到用户";
                    modelMap.addAttribute("tip", tip);
                }


            }
        }
        modelMap.addAttribute("month", month);

        modelMap.addAttribute("weixinId", weixinId);
        return new ModelAndView("driver/workerQty", modelMap);
    }

    // 3.   劳务公司查计件(大铲、龙门吊)
    @RequestMapping(value = "/labourQty")
    public ModelAndView getLabourQty(@RequestParam(value = "workId", required = false) String workId,
                                     @RequestParam(value = "month", required = false) String month, ModelMap modelMap) {


        //用户行为记录
        String actionName = "劳务公司查大铲、龙门吊计件";
        String tips = "";
        recordUserAction(actionName);
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String sMonth = month == null ? sim.format(date) : month;
        modelMap.put("sMonth", sMonth);
        if (!StringUtils.isEmpty(workId)) {
            modelMap.addAttribute("workId", workId);
            try {
                if (driverDAO.getOuterAccount(workId) < 1) {
                    modelMap.addAttribute("tips", "该工号无权限或输入不正确，如需帮助请联系020-34661901");
                    return new ModelAndView("driver/exception", modelMap);
                }
                List<Driver> lstDriver = driverDAO.findDriverQty(workId, month);
                Collections.sort(lstDriver, Comparator.comparing(Driver::getDay));
                //  Collections.sort(lstDriver,(o1, o2) -> o2.getDay().compareTo(o1.getDay()) );
                //Collections.reverse(lstDriver);
                int iSumQty = 0, iSum10 = 0, iSum20 = 0, iSum40 = 0, iSum45 = 0, iSumSpe = 0,iSumDanger = 0;
                for (Driver driver : lstDriver) {
                    iSumQty += (StringUtils.isEmpty(driver.getQty()) ? 0 : driver.getQty());
                    iSum10 += (StringUtils.isEmpty(driver.getCntr10()) ? 0 : Integer.parseInt(driver.getCntr10()));
                    iSum20 += (StringUtils.isEmpty(driver.getCntr20()) ? 0 : Integer.parseInt(driver.getCntr20()));
                    iSum40 += (StringUtils.isEmpty(driver.getCntr40()) ? 0 : Integer.parseInt(driver.getCntr40()));
                    iSum45 += (StringUtils.isEmpty(driver.getCntr45()) ? 0 : Integer.parseInt(driver.getCntr45()));
                    iSumSpe += (StringUtils.isEmpty(driver.getCntrSpe()) ? 0 : Integer.parseInt(driver.getCntrSpe()));
                    iSumDanger += (StringUtils.isEmpty(driver.getDangerCntr()) ? 0 : Integer.parseInt(driver.getDangerCntr()));
                }
                if (!ListUtils.isEmpty(lstDriver)) {
                    Driver driverVirtual = new Driver();
                    driverVirtual.setDay("月总计：");
                    driverVirtual.setCntr10(Integer.toString(iSum10));
                    driverVirtual.setCntr20(Integer.toString(iSum20));
                    driverVirtual.setCntr40(Integer.toString(iSum40));
                    driverVirtual.setCntr45(Integer.toString(iSum45));
                    driverVirtual.setCntrSpe(Integer.toString(iSumSpe));
                    driverVirtual.setDangerCntr(Integer.toString(iSumDanger));
                    driverVirtual.setQty(iSumQty);
                    lstDriver.add(driverVirtual);
                }
                modelMap.put("iSumQty", iSumQty);
                modelMap.addAttribute("lstDriver", lstDriver);
            } catch (Exception e) {
                e.printStackTrace();
                tips = "服务器异常！";
                modelMap.addAttribute("tips", tips);
                return new ModelAndView("driver/exception", modelMap);
            }
        }
        tips = "工号不能为空";
        if (!StringUtils.isEmpty(month)) {
            modelMap.addAttribute("month", month);
        }
        modelMap.addAttribute("tips", tips);
        return new ModelAndView("driver/labourQty", modelMap);
    }

    // 4.   劳务公司查拖车计件(普通作业和过机统计)
    @RequestMapping(value = "/internalTruckQty")
    public ModelAndView internalTruckQty(ModelMap modelMap) {
        SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        String sMonth = sim.format(date);
        modelMap.put("sMonth", sMonth);
        return new ModelAndView("driver/internalTruckQty", modelMap);
    }

    //拖车作业量ajax
    @RequestMapping(value = "/getInternalTruckQty")
    @ResponseBody
    public Result getInternalTruckQty(@RequestParam(value = "workId", required = false) String workId,
                                      @RequestParam(value = "month", required = false) String month) {
        //用户行为记录
        String actionName = "劳务公司查拖车计件";

        if (StringUtils.isEmpty(workId))
            return ResultUtil.error(2, "工号输入不正确");
        if (StringUtils.isEmpty(month))
            ResultUtil.error(2, "时间输入不正确");
        recordUserAction(actionName);
        Map<String, Object> map = new HashMap<>();
        if (!StringUtils.isEmpty(workId)) {
            try {
                List<Driver> lstDriver = driverDAO.findDriverBoth("3", workId, month);
                Collections.sort(lstDriver, Comparator.comparing(Driver::getDay));
                if (lstDriver != null && lstDriver.size() > 0) {
                    // int iSumQty = lstDriver.stream().filter(a -> a.getQty() != null).mapToInt(Driver::getQty).sum();
                    int iSumQty = 0, iSum20 = 0, iSum40 = 0;
                    for (Driver driver : lstDriver) {
                        iSumQty +=  (StringUtils.isEmpty(driver.getQty())?0:driver.getQty());
                        iSum20 +=  (StringUtils.isEmpty(driver.getCntr20())?0:Integer.parseInt(driver.getCntr20()));
                        iSum40 +=  (StringUtils.isEmpty(driver.getCntr40())?0:Integer.parseInt(driver.getCntr40()));
                    }
                    if(!ListUtils.isEmpty(lstDriver)){
                        Driver driverVirtual = new Driver();
                        driverVirtual.setDay("月总计：");
                        driverVirtual.setCntr20(Integer.toString(iSum20));
                        driverVirtual.setCntr40(Integer.toString(iSum40));
                        driverVirtual.setQty(iSumQty);
                        lstDriver.add(driverVirtual);
                    }
                    map.put("iSumQty", iSumQty);
                    map.put("lstDriver", lstDriver);
                }
                List<Driver> lstDriver1 = driverDAO.findDriverBoth("4", month, workId);
                Collections.sort(lstDriver1, Comparator.comparing(Driver::getDay));
                if (lstDriver1 != null && lstDriver1.size() > 0) {
                    // iSumQty1 = lstDriver1.stream().filter(a -> a.getQty() != null).mapToInt(Driver::getQty).sum();
                    int iSumQty1 = 0, iSum20 = 0, iSum40 = 0;
                    for (Driver driver : lstDriver1) {
                        iSumQty1 +=  (StringUtils.isEmpty(driver.getQty())?0:driver.getQty());
                        iSum20 +=  (StringUtils.isEmpty(driver.getCntr20())?0:Integer.parseInt(driver.getCntr20()));
                        iSum40 +=  (StringUtils.isEmpty(driver.getCntr40())?0:Integer.parseInt(driver.getCntr40()));
                    }
                    if(!ListUtils.isEmpty(lstDriver1)){
                        Driver driverVirtual = new Driver();
                        driverVirtual.setDay("月总计：");
                        driverVirtual.setCntr20(Integer.toString(iSum20));
                        driverVirtual.setCntr40(Integer.toString(iSum40));
                        driverVirtual.setQty(iSumQty1);
                        lstDriver1.add(driverVirtual);
                    }
                    map.put("iSumQty1", iSumQty1);
                    map.put("lstDriver1", lstDriver1);
                }
                //  Collections.sort(lstDriver,(o1, o2) -> o2.getDay().compareTo(o1.getDay()) );
                //Collections.reverse(lstDriver);
            } catch (Exception e) {
                e.printStackTrace();
                return ResultUtil.error(0, "服务器错误！");
            }
        }
        return ResultUtil.success(map);
    }

    //NCT司机作业量查询页
    @RequestMapping(value = "/nctDriver")
    public ModelAndView nctDriver(@RequestParam(value = "workerCode", required = false) String workerCode,
                                  ModelMap modelMap) {
        if (!StringUtils.isEmpty(workerCode))
            modelMap.put("workerCode",workerCode);
                SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
                Date date = new Date();
                String month = sim.format(date);
            modelMap.put("month", month);
        return new ModelAndView("driver/nctDriverQty", modelMap);
    }
    //NCT司机作业量数据ajax
    @RequestMapping(value = "/nctDriverQuery")
    @ResponseBody
    public Result nctDriverQuery(@RequestParam(value = "workerCode", required = false) String workerCode,
                                 @RequestParam(value = "month", required = false) String month
                                 ){

        if (StringUtils.isEmpty(workerCode))
            return ResultUtil.error(2, "工号输入不正确");
        if (StringUtils.isEmpty(month))
            ResultUtil.error(2, "时间输入不正确");
        Map map = new HashMap();
        //根据工号查询作业量
        List<Driver> lstDriver = driverDAO.findNctDriverQty(workerCode, month);
        //排序
        Collections.sort(lstDriver, Comparator.comparing(Driver::getDay));
        //统计总量
        //double sumQty = lstDriver.stream().mapToDouble(Driver::getQtyNct).sum();
        double sumQty = 0, sum20E = 0, sum20F = 0, sum40E = 0, sum40F = 0;
        for (Driver driver : lstDriver) {
            sumQty += StringUtils.isEmpty(driver.getQtyNct()) ? 0 : driver.getQtyNct();
            sum20E += StringUtils.isEmpty(driver.getCntr20()) ? 0 : Double.parseDouble(driver.getCntr20());
            sum20F += StringUtils.isEmpty(driver.getCntr20F()) ? 0 : Double.parseDouble(driver.getCntr20F());
            sum40E += StringUtils.isEmpty(driver.getCntr40()) ? 0 : Double.parseDouble(driver.getCntr40());
            sum40F += StringUtils.isEmpty(driver.getCntr40F()) ? 0 : Double.parseDouble(driver.getCntr40F());
        }
        if (!ListUtils.isEmpty(lstDriver)) {
            Driver driverVirtual = new Driver();
            driverVirtual.setDay("月总计：");
            driverVirtual.setCntr20(String.valueOf(sum20E));
            driverVirtual.setCntr20F(String.valueOf(sum20F));
            driverVirtual.setCntr40(String.valueOf(sum40E));
            driverVirtual.setCntr40F(String.valueOf(sum40F));
            driverVirtual.setQtyNct(sumQty);
            lstDriver.add(driverVirtual);
        }
        map.put("workerCode",workerCode);
        map.put("sumQty", sumQty);
        map.put("lstDriver",lstDriver);
        return ResultUtil.success(map);
    }

    //NCT拖车作业量查询
    @RequestMapping(value = "/nctTruckQty")
    public ModelAndView nctTruckQty(@RequestParam(value = "workId", required = false) String workId,
                                    @RequestParam(value = "month", required = false) String month, ModelMap modelMap) {
        if (StringUtils.isEmpty(month)) {
            SimpleDateFormat sim = new SimpleDateFormat("yyyyMM");
            Date date = new Date();
            month = sim.format(date);
        }
        modelMap.put("month", month);
        //根据工号查询作业量
        List<Driver> lstDriver = driverDAO.findNctTruckQty(workId, month);
        //排序
        Collections.sort(lstDriver, Comparator.comparing(Driver::getDay));
        //统计总量
        //double sumQty = lstDriver.stream().mapToDouble(Driver::getQtyNct).sum();
        double sumQty=0,sum20E=0,sum20F=0,sum40E=0,sum40F=0;
        for(Driver driver:lstDriver){
            sumQty += StringUtils.isEmpty(driver.getQtyNct())?0:driver.getQtyNct();
            sum20E += StringUtils.isEmpty(driver.getCntr20())?0:Double.parseDouble(driver.getCntr20());
            sum20F += StringUtils.isEmpty(driver.getCntr20F())?0:Double.parseDouble(driver.getCntr20F());
            sum40E += StringUtils.isEmpty(driver.getCntr40())?0:Double.parseDouble(driver.getCntr40());
            sum40F += StringUtils.isEmpty(driver.getCntr40F())?0:Double.parseDouble(driver.getCntr40F());
        }
        if(!ListUtils.isEmpty(lstDriver)){
            Driver driverVirtual = new Driver();
            driverVirtual.setDay("月总计：");
            driverVirtual.setCntr20(String.valueOf(sum20E));
            driverVirtual.setCntr20F(String.valueOf(sum20F));
            driverVirtual.setCntr40(String.valueOf(sum40E));
            driverVirtual.setCntr40F(String.valueOf(sum40F));
            driverVirtual.setQtyNct(sumQty);
            lstDriver.add(driverVirtual);
        }
        modelMap.put("workId", workId);
        modelMap.put("sumQty", sumQty);
        modelMap.put("driverList", lstDriver);
        return new ModelAndView("driver/nctTruckQty", modelMap);
    }

}
