package goct.query.demo.model;

import lombok.Data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
@Data
public class Truck implements RowMapper, Serializable {

    private Integer truckId;
    private String headNo=""; //车牌
    private String priCode="";
    private String gpassDate=""; //  进闸时间
    private  String  gjobType=""; //作业类型
    private String yequNo="";  //  经过什么设备
    private Integer difftime;  //在码头时间
    private  String fe; //重吉
    //   private String cntrNo;
    private String position=""; // 位置
    private String yardTime="";
    private String bookingNo="";
    private String blNo="";
    private String gisPosition="";
    // 危险品信息
    private String cargoType;
    private String category;
    private String unNo;
    private String dangerName;
    private String dangerWeight;
    //2
    private String outDate="";
    //3
    private String tagNo="";
    private String sztp2="";
    private String vslCd="";
    private String cntrNo="";
    private String ptnrCode="";//
    //4
    private String sicNo="";
    private String truckBody;
    private String companyName;
    private String headType;
    private String weight;
    private String expiryDate;
    private String reason;

    @Override
    public Truck mapRow(ResultSet rs, int rowNum) throws SQLException {
        Truck truck = new Truck();
        truck.setCategory(rs.getString("Category"));
        truck.setUnNo(rs.getString("UNNO"));
        truck.setDangerName(rs.getString("DangerName"));
        truck.setDangerWeight(rs.getString("Weight"));
        return truck;
    }
}

