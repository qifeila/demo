package goct.query.demo.mapper.oracle91;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

public class ConSql {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private DruidDataSource dataSource;

    private Connection connection;

    public void getDateSource() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:sqlserver://10.1.0.122:1433;DatabaseName=eportal");
            dataSource.setUsername("eportal");
            dataSource.setPassword("eportal!@#");
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //启动连接
            connection = dataSource.getConnection();
        }

    }
    //数据源配置
    public void getDateSource1() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:oracle:thin:@//10.1.0.47:1521/goctcxk");
            dataSource.setUsername("eportal");
            dataSource.setPassword("success");
            dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            //启动连接
            connection = dataSource.getConnection();
        }


    }
}


