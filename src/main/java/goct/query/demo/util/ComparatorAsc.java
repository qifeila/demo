package goct.query.demo.util;

import goct.query.demo.model.Inventory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorAsc  implements Comparator {
    public int compare(Object obj0, Object obj1) {
        Inventory user0=(Inventory)obj0;
        Inventory user1=(Inventory)obj1;

        int flag=user0.getQtySum().compareTo(user1.getQtySum());
        if(flag==0){
            return user0.getItem().compareTo(user1.getItem());
        }else{
            return flag;
        }
    }
    public void sortUsingJava8(List<Inventory> inventories){
        Collections.sort(inventories, (o1,o2) -> o2.getQtySum().compareTo(o1.getQtySum()));
    }
}
