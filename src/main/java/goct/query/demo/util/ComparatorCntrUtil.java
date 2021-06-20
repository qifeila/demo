package goct.query.demo.util;

import goct.query.demo.model.Inventory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/*
根据list<inventory> 中的inventory.qty 属性 将list排序
 */
public class ComparatorCntrUtil implements Comparator {
    public int compare(Object obj0, Object obj1) {
        Inventory user0=(Inventory)obj0;
        Inventory user1=(Inventory)obj1;

        //首先比较qty，如果qty相同，则比较 item  降序
        Integer a;
        Integer b;

        int flag=user1.getQtySum().compareTo(user0.getQtySum());
        if(flag==0){
            return user1.getItem().compareTo(user0.getItem());
        }else{
            return flag;
        }
    }
    public void sortUsingJava8(List<Inventory> inventories){
        Collections.sort(inventories, (Inventory o1, Inventory o2) -> o1.getQtySum().compareTo(o2.getQtySum()));
    }

    public void sortUsingJava7(List<Inventory> inventories){
        Collections.sort(inventories, new Comparator<Inventory>() {
            @Override
            public int compare(Inventory o1, Inventory o2) {
                return o1.getQtySum().compareTo(o2.getQtySum());
            }
        });
    }
}
