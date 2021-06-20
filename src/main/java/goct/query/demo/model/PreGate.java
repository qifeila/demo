package goct.query.demo.model;

import lombok.Data;

import java.util.Date;
@Data
public class PreGate {
    private String cntr_no;            //箱号
    private String cntr_repair_state;      //箱状态
    private String extract_flag;      //coparn表插入状态
    private String repairer;             //录入人
    private String repair_remark;        //备注
    private Date repair_time;            //提交时间
}
