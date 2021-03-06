package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.GateMapper;
import goct.query.demo.mapper.oracle91.GoctGateInoutMapper;
import goct.query.demo.mapper.oracleGoct.CntrInAndOutMapper;
import goct.query.demo.model.CntrInAndOut;
import goct.query.demo.model.Gate;
import goct.query.demo.model.GoctGateInout;
import goct.query.demo.service.QrcodeService;
import goct.query.demo.util.Data2JsonUtil;
import goct.query.demo.util.LayUiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class GateController {
    @Autowired
    GateMapper gateMapper;
    @Autowired
    ActionMapper actionMapper;
    @Autowired
    QrcodeService qrcodeService;

    @Autowired
    CntrInAndOutMapper cntrInAndOutMapper;

    @Autowired
    GoctGateInoutMapper goctGateInoutMapper;

    /**
     * wmm
     * @param modelMap
     * @return
     */
    @RequestMapping("/getCntrByWeeks")
    public ModelAndView getCntrByWeeks(ModelMap modelMap){

        List<CntrInAndOut> cntrInAndOuts=cntrInAndOutMapper.findCntrInAndOut2_3();
        String cntrNumData=Data2JsonUtil.getCntrNumData(cntrInAndOutMapper.findCntrInAndOut2_3(),cntrInAndOutMapper.findCntrInAndOut3_2());
        modelMap.addAttribute("cntrNumData",cntrNumData);
        System.out.println(cntrInAndOuts);
        modelMap.addAttribute("lastWeek",cntrInAndOuts.size()-4);
        return new ModelAndView("gate/cntrInAndOut",modelMap);
    }

    /**
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/getCountTable")
    public ModelAndView getCountTable(ModelMap modelMap){

        return new ModelAndView("gate/countTable",modelMap);
    }

    @RequestMapping("/getCountTableData")
    @ResponseBody
    public LayUiResult<GoctGateInout> getCountTableData(){
        List<GoctGateInout> goctGateInoutList = goctGateInoutMapper.findAll();
        Integer countHeadNum=new Integer(0);
        BigDecimal countGfE=new BigDecimal(0);
        BigDecimal countGfF=new BigDecimal(0);
        BigDecimal countGoE=new BigDecimal(0);
        BigDecimal countGoF=new BigDecimal(0);
        BigDecimal countTeu=new BigDecimal(0);
        for (GoctGateInout goctGateInout: goctGateInoutList) {
            countHeadNum=countHeadNum+goctGateInout.getHeadNum();
            countGfE=countGfE.add(goctGateInout.getGfE());
            countGfF=countGfF.add(goctGateInout.getGfF());
            countGoE=countGoE.add(goctGateInout.getGoE());
            countGoF=countGoF.add(goctGateInout.getGoF());
            countTeu=countTeu.add(goctGateInout.getTeu());

        }
        GoctGateInout goctGateInout=new GoctGateInout();
        goctGateInout.setYmd("??????");
        goctGateInout.setHeadNum(countHeadNum);
        goctGateInout.setGfE(countGfE);
        goctGateInout.setGfF(countGfF);
        goctGateInout.setGoE(countGoE);
        goctGateInout.setGoF(countGoF);
        goctGateInout.setTeu(countTeu);
        goctGateInoutList.add(goctGateInout);
        LayUiResult result=new LayUiResult();
        result.setCode(0);
        result.setMsg("success");
        result.setCount(goctGateInoutList.size());
        result.setData(goctGateInoutList);
        return result;

    }
    @RequestMapping(value = "/gate")
    public ModelAndView getGateKpi(ModelMap modelMap)
    {
        //gateMapper.GetTestPro();?????????????????????
        String data = Data2JsonUtil.getJson(gateMapper.getTruckTime(),gateMapper.getTruckNum(),gateMapper.getThroughput());

        //?????? ??? ('??????','??????','??????','??????') ??????  ????????????
        String dataFE = Data2JsonUtil.getFEdata(gateMapper.getFin(),gateMapper.getFout(),gateMapper.getEin(),gateMapper.getEout());

        List<Gate> gateList = new ArrayList<>();
        Date today  = new Date();  //today
        Calendar cal=Calendar.getInstance();
        System.out.println(cal);

        cal.add(Calendar.DATE,-1);
        Date yesterday=cal.getTime();   //??????
        cal.add(Calendar.DATE,-29);

        Date lastMonth=cal.getTime();   //??????
        //  System.out.println(cal);
        // System.out.println(now);
        // new SimpleDateFormat("yyyy-MM-dd").format(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//?????????????????????????????????
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        String year = dateFormatYear.format(today);   //??????
       // String today1 = dateFormat.format( today );  //yesterday
        String time = dateFormat.format( yesterday );  //yesterday
        String lastMonth1 = dateFormat.format( lastMonth );  //lastMonth

        gateList = gateMapper.getYesterday(time);
        if (gateList.size()==5){
        gateList.get(0).setKpi("??????????????????(???)");
        gateList.get(1).setKpi("??????????????????(U)");
        gateList.get(2).setKpi("??????????????????(???)");
        gateList.get(3).setKpi("???????????????(U)");
        gateList.get(4).setKpi("??????????????????????????????");
        }

        //???????????? ??????list
        List<Gate> gateList1 = Data2JsonUtil.getGateList(
                gateMapper.getTruckTimeByYear(year),gateMapper.getTruckNumByYear(year),gateMapper.getThroughputByYear(year));

        //???????????? ??????list    ('??????','??????','??????','??????')
        List<Gate> feListByMonth = Data2JsonUtil.getFEdataByMonth(gateMapper.getFinByYear(year),gateMapper.getFoutByYear(year),
                gateMapper.getEinByYear(year),gateMapper.getEoutByYear(year));

        //??????????????????
     //    List<Gate> gateListFE = gateMapper.getYesterdayFE(time);


       // Gate gate = Data2JsonUtil.getGateFE(gateListFE);
        String status = "1";

        modelMap.addAttribute("status",status);
        modelMap.addAttribute("lastMonth",lastMonth1);
        modelMap.addAttribute("data",data);
        modelMap.addAttribute("dataFE",dataFE);
        modelMap.addAttribute("feListByMonth",feListByMonth);

        modelMap.addAttribute("gateList",gateList);
        //gateList1??????????????????KPI
        modelMap.addAttribute("gateList1",gateList1);

        return new ModelAndView("gate/gateKpi",modelMap);

    }
    @RequestMapping(value = "/gateNct",method = RequestMethod.POST)
    public ModelAndView getNctGateKpi(ModelMap modelMap) {
        //gateMapper.GetTestPro();?????????????????????
        String data = Data2JsonUtil.getJson(gateMapper.getTruckTime(),gateMapper.getTruckNum(),gateMapper.getThroughput());
        List<Gate> gateList = new ArrayList<>();
        //Date now  new Date();
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);

        Date now=cal.getTime();
        //  System.out.println(cal);
        // System.out.println(now);
        // new SimpleDateFormat("yyyy-MM-dd").format(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//?????????????????????????????????
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        String year = dateFormatYear.format(now);
        String time = dateFormat.format( now );
        gateList = gateMapper.getYesterday(time);
        if (gateList.size()==5){
            gateList.get(0).setKpi("1??????????????????(???)");
            gateList.get(1).setKpi("1??????????????????(U)");
            gateList.get(2).setKpi("1??????????????????(???)");
            gateList.get(3).setKpi("1???????????????(U)");
            gateList.get(4).setKpi("1??????????????????????????????");
        }
        String status = "2";
        modelMap.addAttribute("status",status);
        List<Gate> gateList1 = Data2JsonUtil.getGateList(
                gateMapper.getTruckTimeByYear(year),gateMapper.getTruckNumByYear(year),gateMapper.getThroughputByYear(year));
        modelMap.addAttribute("data",data);
        modelMap.addAttribute("gateList",gateList);
        //gateList1??????????????????KPI
        modelMap.addAttribute("gateList1",gateList1);

        return new ModelAndView("gate/gateKpi",modelMap);


    }

//    @RequestMapping(value = "/getQR")
//    public ModelAndView getQR(ModelMap modelMap) throws FrameGrabber.Exception {
//
//
//        Java2DFrameConverter java2dConverter = new Java2DFrameConverter();  //????????????Frame???BufferedImage???????????????
//
///**
// * ???????????????
// */
//
//
//        //public static OpenCVFrameGrabber grabber;
//        grabber.start();
//
//        Frame frame = grabber.grabFrame();
//        BufferedImage bImage =java2dConverter.getBufferedImage(frame);
//        String value = qrcodeService.QrParse(bImage);
//        System.out.println(value);
//
//        modelMap.addAttribute("value",value);
//
//
//
//
//
//        return new ModelAndView("gate/gateQRcode",modelMap);
//    }
//    @RequestMapping(value = "/nextQR")
//    public ModelAndView nextQR(ModelMap modelMap) throws FrameGrabber.Exception {
//
//        return new ModelAndView("gate/gateInOut.html",modelMap);
//    }


}
