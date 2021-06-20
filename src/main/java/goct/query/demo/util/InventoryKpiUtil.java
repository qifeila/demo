package goct.query.demo.util;

import goct.query.demo.model.Gate;
import goct.query.demo.model.Inventory;

import java.util.*;

public class InventoryKpiUtil {
    /*public static Map<String,List<Inventory>> getGateList(List<Inventory> inventories){
        // Integer sum = 0;
        Map<String,List<Inventory>> map= new HashMap<>();
        List<Inventory> inventoryCom = new ArrayList<>();//qita
        List<Inventory> inventoryKind = new ArrayList<>();//箱型分类
        List<Inventory> inventoryInOut = new ArrayList<>();//进出口
        List<Inventory> fInventoryKind = new ArrayList<>();//重柜堆存时间
        List<Inventory> bInventoryListCom = new ArrayList<>(); //栏柜情况
        List<Inventory> eInventoryPtnrCode = new ArrayList<>();//各船公司吉柜数量
        List<Inventory> fInventoryPtnrCode = new ArrayList<>();//各船公司重柜数量
        for (Inventory inventory:inventories){
            if (inventory.getKpi().equals("箱型分类"))
                inventoryKind.add(inventory);
            if (inventory.getKpi()=="进出口")
                inventoryInOut.add(inventory);
            if (inventory.getKpi().equals("重吉")||inventory.getKpi().equals("在场箱总数")
                    ||inventory.getKpi().equals("重柜堆存时间"))
                inventoryCom.add(inventory);
            if (inventory.getKpi().equals("重柜货物类型"))
                fInventoryKind.add(inventory);
            if (inventory.getKpi().equals("烂柜情况"))
                bInventoryListCom.add(inventory);
            if (inventory.getKpi().equals("各船公司吉柜数量"))
                eInventoryPtnrCode.add(inventory);
            if (inventory.getKpi().equals("各船公司重柜数量"))
                fInventoryPtnrCode.add(inventory);
        }


        map.put("箱型分类",inventoryKind);
        map.put("进出口",inventoryInOut);
        map.put("其他",inventoryCom);
        map.put("重柜货物类型",fInventoryKind);
        map.put("烂柜情况",bInventoryListCom);
        map.put("各船公司吉柜数量",eInventoryPtnrCode);
        map.put("各船公司重柜数量",fInventoryPtnrCode);
        //List<Inventory> list=
       // map.p


        return map;
    }*/
    public static   List<Inventory> getSum(List<Inventory> inventoriesE,List<Inventory> inventoriesF){
        List<Inventory> list= new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Inventory inventory: inventoriesE){
            set.add(inventory.getItem());
            inventory.setQtyE(Integer.parseInt(inventory.getQty()));
            list.add(inventory);
        }
        for (Inventory inventory: inventoriesF){
            //set.add(inventory.getItem());
            //如果不含 箱属  则增加inventory ,含 则更改原来list中的inventory 的qtyf赋值
            if (set.contains(inventory.getItem())){
                for (Inventory inventory1 : list){
                    if (inventory.getItem().equals(inventory1.getItem())){
                        inventory1.setQtyF(Integer.parseInt(inventory.getQty()));
                        break;
                    }
                }
            }else{
                inventory.setQtyF(Integer.parseInt(inventory.getQty()));
                list.add(inventory);
            }
        }
        return list;
    }
    /*
    输入参数 降序排列的list
     */
    public static   List<Inventory> getSupData(List<Inventory> inventories){
        int a = Integer.parseInt(inventories.get(0).getQty());
        inventories.get(0).setQtyE(0);//初值0  qtye为柱形图的辅助data
        //初值已赋值0 所以i从1开始
        for(int i= 1;i<inventories.size();i++){
            a= a - Integer.parseInt(inventories.get(i).getQty());
            inventories.get(i).setQtyE(a);
        }
        return inventories;
    }
/*
将list qtySum 的500一下 去除 归并“其他”
 */
    public static   List<Inventory> getOptimizeList(List<Inventory> inventories){
        Inventory inventory1 = new Inventory();
        List<Inventory>  list_remove = new ArrayList<>();
        inventory1.setItem("其他");
        for (Inventory inventory3: inventories){
            if (inventory3.getQtySum()<500){
                inventory1.setQtyE(inventory3.getQtyE()+inventory1.getQtyE());
                inventory1.setQtyF(inventory3.getQtyF()+inventory1.getQtyF());
                list_remove.add(inventory3);
            }
        }
        inventories.add(inventory1);
        inventories.removeAll(list_remove);
        return inventories;
    }

    //public static void

}
