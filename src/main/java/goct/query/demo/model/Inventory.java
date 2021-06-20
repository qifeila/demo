package goct.query.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Inventory {
    private Integer inventoryId;

    private String cntrNo="";
    private String fe="";  //吉重
    private String sztp="";//尺寸 sizetype
    private String pod="";
    private String pol="";
    private String ixCd="";//进出口
    private String tier="";
    private String ptnrCode="";//箱属 operator
    private String wgt="";
    private String blNo="";
    private String bookingNo="";
    private String flag="";
    private String vgmChk="";
    private String ciqChk="";
    private String tholdChk="";//码头上锁标志

    //
    private String priCode="";
    private String storageCode = "";
    private String total="";
    //
    private String cargoType="";//废纸等类型
    private String requester="";//检方
    private String status="";
    //
    private String origin;
    private String operationTime;
    private String description;
    private String changeDate;//放行日期
    private String passDate;//过机日期

    private  String terminal;//堆放港区
    private  String inTime;
    private  String vslNm;

    //危险品
    private  String imdg;  //危险品累
    private  String unno;
    private  String dangerName;
    private  String inYardTime;
    private  String pos;

    //kpi
    private  String kpi;
    private  String item;
    private  String qty;
    private  Integer qtyF = 0;
    private  Integer qtyE = 0;
    private  Integer qtySum = 0;








}
