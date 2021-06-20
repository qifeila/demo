package goct.query.demo.model;

import lombok.Data;

import java.util.Date;


//用户行为的统计
@Data
public class Action {
    private String actionName;
    private String time;
    private Integer frequency;
    private Integer actionId;
}
