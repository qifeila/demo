package goct.query.demo.model;

import lombok.Data;

/**
 * @author sunyz
 * @date 2020/9/28 11:05
 */
@Data
public class GpsDeviceLog {
    private String deviceId;
    private String longitude;
    private String latitude;
    private String speed;
    private String lstDateTim;
    private String deviceType;
    private String direction;
    private String status;
    private String deviceNam;
    private String portCode;
    private String trkDir;

}
