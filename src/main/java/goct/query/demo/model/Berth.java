package goct.query.demo.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;


import java.util.Date;

//大船
@Data
public class Berth {
    private Integer berthId;

    private String vslCd="";
    private String callYear="";
    private String callSeq="";
    private String vslName="";  //船名
    private String oldVslName="";// 中文
    private String inLane=""; //进航线
    private String outLane="";// 出
    private String inVoy=""; //航次
    private String outVoy="";//
    private String yardClose=""; //堆场关闭时间
    private String inmarsat="";//船舶IMO
    private String status=""; //状态 2
    private String eta=""; //预计到达时间
    private String etb="";  //预计靠泊时间
    private String etd="";  //2离泊
    private String atb="";  //2实际
    private String ptnrCode="";//船属  箱属
    private String berthNo="";//泊位
    private String fromBitt="";//桩开始
    private String toBitt="";//结束
    private String ata="";//
    private String atd="";//
    private String atw="";//实际开工
    private String etc="";//预计完成
    private String atc="";//
    private String atv="";//
    private Integer total;//
    private Integer remainQty;//
    private Integer loQty;//
    private Integer dsQty;//
    private double ratio;//
    private double vqvh; //
    private String terminal;


}
