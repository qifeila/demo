package goct.query.demo.mapper.sqlServer122;

import goct.query.demo.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
@Configuration
public class DangerCntrDao {
    @Autowired
    @Qualifier("ds3JdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    //根据箱号查找危险品信息
    public Truck getByCntrNo(String cntrNo){
        String sql = "SELECT TOP(1) * FROM [ePortal].[DangerReefer].[Hazardous] " +
                "where OpTime is null and ContainerNo = ? order by InYardTime desc ";
        String[] params = {cntrNo};
        Truck truck = null;
        try {
            truck = (Truck) jdbcTemplate.queryForObject(sql, params, new Truck());
        } catch (Exception e) {
            System.out.println(e);
        }
        return truck;
    }
}
