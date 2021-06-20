package goct.query.demo.model;

import lombok.Data;

import java.util.Date;
@Data
public class GateCheckInfo {
    private String cntr_no;
    private String cntr_id;
    private String dmg_cond;
    private String remark;
    private String fe;
    private Date update_time;
    private String account;
    private GateEir gateEir;

}
