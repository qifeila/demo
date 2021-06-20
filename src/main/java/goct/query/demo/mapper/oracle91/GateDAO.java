package goct.query.demo.mapper.oracle91;

import goct.query.demo.model.Driver;
import goct.query.demo.model.Edi;
import goct.query.demo.model.Gate;
import goct.query.demo.model.Inventory;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class GateDAO {
   /* @Autowired
    private JdbcTemplate jdbcTemplate;

    List<Gate> getTruckTime (){
        PreparedStatement pst = null ;
        ResultSet rs = null;
        List<Inventory> inventoryList = new ArrayList<>();

        String sql = "";
        try{
            pst=con.prepareStatement(sql);
            rs=pst.executeQuery();
            while(rs.next()){
                Inventory inventory = new Inventory();
                // Berth berth = new Berth();

                inventory.setCntrNo(rs.getString("CNTR_NO"));
                inventory.setSztp(rs.getString("SZTP"));
                if (!StringUtils.isEmpty(rs.getString("IMDG")))
                    inventory.setImdg(rs.getString("IMDG"));
                if (!StringUtils.isEmpty(rs.getString("UNNO")))
                    inventory.setUnno(rs.getString("UNNO"));
                if (!StringUtils.isEmpty(rs.getString("DANGER_NAME")))
                    inventory.setDangerName(rs.getString("DANGER_NAME"));
                inventory.setWgt(rs.getString("WGT"));
                inventory.setInYardTime(rs.getString("IN_YARD_TIME"));
                inventory.setPos(rs.getString("POS"));


                inventoryList.add(inventory);


            }
            rs.close();
            pst.close();
            connection.close();
            dataSource.close();
        }
        catch (Exception e){e.printStackTrace();}








        return inventoryList;
    };
    List<Gate> getTruckNum ();
    List<Gate> getThroughput ();*/
}
