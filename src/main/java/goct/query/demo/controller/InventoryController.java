package goct.query.demo.controller;

import goct.query.demo.mapper.oracle91.ActionMapper;
import goct.query.demo.mapper.oracle91.InventoryDAO;
import goct.query.demo.model.Inventory;
import goct.query.demo.util.ComparatorAsc;
import goct.query.demo.util.ComparatorCntrUtil;
import goct.query.demo.util.InventoryKpiUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
public class InventoryController {
    @Autowired
    InventoryDAO inventoryDAO;
    @Autowired
    ActionMapper actionMapper;
    public void recordUserAction(String actionName){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
        String time = dateFormat.format( now );
        actionMapper.insertActionSim(actionName,time);
    }

    //在场箱查询
    @RequestMapping(value = "/getInventoryInPort")
    public ModelAndView getInventoryInPort(@RequestParam(value = "container")String container, ModelMap modelMap){
        if(!StringUtils.isEmpty(container)){
            //用户行为记录
            String actionName = "在场信息查询";
            recordUserAction(actionName);
            List<Inventory> inventoryList = inventoryDAO.findInventoryInPort(container);


            modelMap.addAttribute("inventoryList",inventoryList);}
        modelMap.addAttribute("container",container);
        return new ModelAndView("inventory/inventoryInPort",modelMap);
    }
    //在场箱查询
    @RequestMapping(value = "/getInventoryInPort1")
    public ModelAndView getInventoryInPort(ModelMap modelMap){
        return new ModelAndView("inventory/inventoryInPort",modelMap);
    }
    //可提吉柜查询
    @RequestMapping(value = "/getInventoryNumE")
    public ModelAndView getInventoryNumE(@RequestParam(value = "ptnrCode")String ptnrCode, ModelMap modelMap){
        if(!StringUtils.isEmpty(ptnrCode)){
            //用户行为记录
            String actionName = "可提吉柜查询";
            recordUserAction(actionName);
            List<Inventory> inventoryList = inventoryDAO.findInventoryNumE(ptnrCode);
            modelMap.addAttribute("inventoryList",inventoryList);
            modelMap.addAttribute("ptnrCode",ptnrCode);}
        return new ModelAndView("inventory/inventoryNumE",modelMap);
    }
    //可提吉柜查询
    @RequestMapping(value = "/getInventoryNumE1")
    public ModelAndView getInventoryNumE(ModelMap modelMap){
        return new ModelAndView("inventory/inventoryNumE",modelMap);
    }

    //查验信息查询
    @RequestMapping(value = "/getCheckInfo")
    public ModelAndView getCheckInfo(@RequestParam(value = "container")String container, ModelMap modelMap){
        if(!StringUtils.isEmpty(container)){
            //用户行为记录
            String actionName = "查验信息查询";
            recordUserAction(actionName);
            try {
                inventoryDAO.getDateSource();
                List<Inventory> inventoryList = inventoryDAO.findCheckInfo(container);
                modelMap.addAttribute("inventoryList",inventoryList);
            }catch (Exception e){
                System.out.println("异常");
                e.printStackTrace();
            }if (container.length()<12){//查柜进度
                try {
                    inventoryDAO.getDateSource();
                    List<Inventory> inventoryList1 = inventoryDAO.getCheckInfo(container);
                    modelMap.addAttribute("inventoryList1",inventoryList1);
                }catch (Exception e){
                    System.out.println("异常");
                    e.printStackTrace();
                }
            }



            modelMap.addAttribute("container",container);}
        return new ModelAndView("inventory/checkInfo",modelMap);
    }
    //查验信息查询
    @RequestMapping(value = "/getCheckInfo1")
    public ModelAndView getCheckInfo(ModelMap modelMap){
        return new ModelAndView("inventory/checkInfo",modelMap);
    }
    //过机信息查询
    @RequestMapping(value = "/getPassingInfo")
    public ModelAndView getPassingInfo(@RequestParam(value = "container")String container, ModelMap modelMap){
        //用户行为记录
        String actionName = "过机信息查询";
        recordUserAction(actionName);
        if(!StringUtils.isEmpty(container)&&container.length()<22){
            List<Inventory> inventoryList = inventoryDAO.findPassingInfo1(container);
            modelMap.addAttribute("inventoryList",inventoryList);
            modelMap.addAttribute("container",container);
        }else if (!StringUtils.isEmpty(container)&&container.length()>21){
            List<Inventory> inventoryList = inventoryDAO.findPassingInfo(container);
            modelMap.addAttribute("inventoryList",inventoryList);
            modelMap.addAttribute("container",container);

        }

        return new ModelAndView("inventory/passingInfo",modelMap);
    }
    //过机信息查询
    @RequestMapping(value = "/getPassingInfo1")
    public ModelAndView getPassingInfo(ModelMap modelMap){

        return new ModelAndView("inventory/passingInfo",modelMap);
    }

    //危险品查询
    @RequestMapping(value = "/getDangerousInventory")
    public ModelAndView getDangerousInventory(@RequestParam(value = "container")String container, ModelMap modelMap){
        if(!StringUtils.isEmpty(container)){
            //用户行为记录
            String actionName = "危险品查询";
            recordUserAction(actionName);
            try{inventoryDAO.getDateSource();}catch (Exception e){
                e.printStackTrace();
                System.out.println("数据池连接异常");
            }
            List<Inventory> inventoryList = inventoryDAO.findDangerousInventory(container);
            modelMap.addAttribute("inventoryList",inventoryList);
            modelMap.addAttribute("container",container);}


        return new ModelAndView("inventory/dangerousInventory",modelMap);
    }
    //危险品查询
    @RequestMapping(value = "/getDangerousInventory1")
    public ModelAndView getDangerousInventory(ModelMap modelMap){
        return new ModelAndView("inventory/dangerousInventory",modelMap);
    }
    //inventoryKpi 查询
    @RequestMapping(value = "/inventory")
    public ModelAndView getInventoryKpi(ModelMap modelMap){
        List<Inventory> inventories = inventoryDAO.findInventoryKpi();
        List<Inventory> inventoryCom = new ArrayList<>();//qita
        List<Inventory> inventoryKind = new ArrayList<>();//箱型分类
        List<Inventory> inventoryInOut = new ArrayList<>();//进出口
        List<Inventory> fInventoryKind = new ArrayList<>();//重柜货物类型
        List<Inventory> bInventoryInfo = new ArrayList<>(); //栏柜情况
        List<Inventory> eInventoryPtnrCode = new ArrayList<>();//各船公司吉柜数量
        List<Inventory> fInventoryPtnrCode = new ArrayList<>();//各船公司重柜数量
        for (Inventory inventory:inventories){
            if (inventory.getKpi().equals("箱型分类"))
                inventoryKind.add(inventory);
            if (inventory.getKpi().equals("进出口"))
                inventoryInOut.add(inventory);
            if (inventory.getKpi().equals("重吉")||inventory.getKpi().equals("在场箱总数")
                    ||inventory.getKpi().equals("重柜堆存时间"))
                inventoryCom.add(inventory);
            if (inventory.getKpi().equals("重柜货物类型"))
                fInventoryKind.add(inventory);
            if (inventory.getKpi().equals("烂柜情况"))
                bInventoryInfo.add(inventory);
            if (inventory.getKpi().equals("各船公司吉柜数量"))
                eInventoryPtnrCode.add(inventory);
            if (inventory.getKpi().equals("各船公司重柜数量"))
                fInventoryPtnrCode.add(inventory);
        }
        int sum = 0;
        for (Inventory inventory :bInventoryInfo){
            sum = sum + Integer.parseInt(inventory.getQty());
        }
        Inventory inventory = new Inventory();
        inventory.setItem("总量");
        inventory.setQty(sum+"");
        bInventoryInfo.add(inventory);

        for (Inventory inventory1 :bInventoryInfo ){
            //qtySum 用作排序用
            inventory1.setQtySum(Integer.parseInt(inventory1.getQty()));
        }
        ComparatorCntrUtil comparator=new ComparatorCntrUtil();
        Collections.sort(bInventoryInfo, comparator);//降序排序

        bInventoryInfo=InventoryKpiUtil.getSupData(bInventoryInfo);//获取柱状图辅助数据

        List<Inventory> inventoryPtnrCode = InventoryKpiUtil.getSum(eInventoryPtnrCode,fInventoryPtnrCode);
        for (Inventory inventory2 : inventoryPtnrCode){
            //qtySum 用作排序用
            inventory2.setQtySum(inventory2.getQtyF()+inventory2.getQtyE());
        }
        inventoryPtnrCode=InventoryKpiUtil.getOptimizeList(inventoryPtnrCode);//去除500一下的qtySum
        ComparatorAsc comparator1=new ComparatorAsc();
     //   Collections.sort(inventoryPtnrCode, comparator1);//升序排序

      //  comparator.sortUsingJava7(inventoryPtnrCode);//升序


       /* for (int i= 0;i<inventoryPtnrCode.size();i++){
            System.out.println("777  "+inventoryPtnrCode.get(i).getQtySum());

        }

        comparator1.sortUsingJava8(inventoryPtnrCode); //jaingxu
        for (int i= 0;i<inventoryPtnrCode.size();i++){
            System.out.println("888  "+inventoryPtnrCode.get(i).getQtySum());

        }*/


        Collections.sort(inventoryPtnrCode, comparator1);//升序排序
        /*for (int i= 0;i<inventoryPtnrCode.size();i++){
            System.out.println("999  "+inventoryPtnrCode.get(i).getQtySum());

        }
*/





        //inventoryPtnrCode=InventoryKpiUtil.getData(inventoryPtnrCode);
        modelMap.addAttribute("inventoryKind",inventoryKind);
        modelMap.addAttribute("inventoryInOut",inventoryInOut);
        modelMap.addAttribute("inventoryCom",inventoryCom);
        modelMap.addAttribute("fInventoryKind",fInventoryKind);
        modelMap.addAttribute("bInventoryInfo",bInventoryInfo);
        modelMap.addAttribute("inventoryPtnrCode",inventoryPtnrCode);

        return new ModelAndView("inventory/inventoryKpi",modelMap);
    }

}
