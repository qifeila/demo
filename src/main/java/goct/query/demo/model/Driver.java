package goct.query.demo.model;

import lombok.Data;

@Data
public class Driver {
    private String driverId;
    private String cntr10;
    private String cntr20;
    private String cntr40;
    private String cntr45;
    private String cntrSpe;
    private String dangerCntr;
    private Integer qty;
    private String day; //6 数字

    private String catosId;
    private String catosId2;
    private String catosId3;
    private String catosId4;

    private String driverName;
    //nct信息包含 F/E  上面字段不加为E
    private String cntr20F;
    private String cntr40F;
    private Double qtyNct;//nct统计会有0.5存在

}
