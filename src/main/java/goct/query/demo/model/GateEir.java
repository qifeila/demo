package goct.query.demo.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class GateEir {
    //箱号
    private String cntr_no;
    //箱属
    private String ptnr_code;
    private String old_vsl_nm;
    private String out_voy;
    private String pod;
    private double set_temp;
    private double wgt;
    private String sztp; //箱型
    private String seal_no3;
    private String unno;
    private String imdg;
    private double os_height;
    private double os_port;
    private double os_stbd;
    private String companyName;
    private String driverName;
    private String mobilePhone;
    private String truck_no;
    //出码头时间
    private Date update_time;
    private String so_no;
    private String cntr_id;
    private String cntr_uid;
    private String data_flg;
    private String blockchain_flg;
    private String job_type;
    private String fe;
    private String job_no;
    //验柜好坏
    private char cntr_state;
    private String remark;
    //验柜录入信息关联表
    private List<GateCheckInfo> gateCheckList;

}
