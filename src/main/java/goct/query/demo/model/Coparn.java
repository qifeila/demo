package goct.query.demo.model;

import lombok.Data;

import java.util.Date;
@Data
//外修箱update表
public class Coparn {
    private String odr_no;         //唯一主键
    private String cntr_no;              //箱号
    private String repair_state;      //箱状态
    private String repairer;             //录入人
    private String repair_remark;        //备注
    private Date repair_time;            //提交时间
    private Date used_date;              //进闸时间，为空标识可update
}
