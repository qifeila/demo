package goct.query.demo.mapper.oracle91;

import com.alibaba.druid.pool.DruidDataSource;
import goct.query.demo.model.Inventory;
import goct.query.demo.model.Driver;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DriverDAO {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("ds2JdbcTemplate")
    private JdbcTemplate jdbcTemplate2;

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

    public String getEmployeeId(String weixinId) {


        PreparedStatement pst = null;
        ResultSet rs = null;

        String employeeId = null;
        List<Driver> drivers = new ArrayList<>();

        String sql = "SELECT TOP 1000 [EmployeeID] FROM [ePortal].[SystemManagement].[User] where WeixinID=? ";
        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, weixinId);
            rs = pst.executeQuery();
            while (rs.next()) {
                //默认微信ID和employeeid 一一对应
                employeeId = rs.getString("EmployeeID");//m
                //  System.out.println(employeeId+"11111111111111");
            }
            rs.close();
            pst.close();
            // connection.close();两个方法连用暂不关闭
            // dataSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeId;

    }

    public List<Driver> findDriver(String employeeId) {
        //  System.out.println("sql"+employeeId);
        // weixinId = "'"+weixinId+"'";
        //  System.out.println("sql"+weixinId);

        PreparedStatement pst = null;
        ResultSet rs = null;


        List<Driver> drivers = new ArrayList<>();
        // String container1 = container.toUpperCase();


        String sql = "SELECT  [Name],[MobilePhone],[CatosID],[CatosID2] ,[CatosID3],[CatosID4] \n" +
                "  FROM [ePortal].[HumanResources].[Employee]\n" +
                "  where Employeeid =?";

        try {
            pst = connection.prepareStatement(sql);
            pst.setString(1, employeeId);
            rs = pst.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                //默认微信ID和employeeid 一一对应
                driver.setCatosId(rs.getString("CatosID"));//m
                driver.setCatosId2(rs.getString("CatosID2"));
                driver.setCatosId3(rs.getString("CatosID3"));//m
                driver.setCatosId4(rs.getString("CatosID4"));
                driver.setDriverName(rs.getString("Name"));
                drivers.add(driver);
            }
            rs.close();
            pst.close();
            connection.close();
            //  dataSource.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //  rs.close();
            //  pst.close();
            //  connection.close();
            dataSource.close();
        }
        return drivers;
    }

    //判断catos龙门吊、大铲工号是否为外部员工,返回查询条数
    public int getOuterAccount(String catosId){
        String sql = "select Count(1) from tb_staff where STAFF_CD =? AND (DEPT_CD LIKE '%清远%' " +
                "OR DEPT_CD LIKE '%泰忠%' OR DEPT_CD LIKE '%宏港%' OR DEPT_CD LIKE '%海诚%' OR DEPT_CD LIKE '%飞德骏%' "+
                "OR DEPT_CD LIKE '%新沙%')";
        String [] parm = {catosId};
        return jdbcTemplate2.queryForObject(sql,parm,Integer.class);
    }
    //判断NCT计件查询的工号是否为内部工号

    //1. 司机查件
    public List<Driver> findDriverQty(String catosId, String month) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_workload(?,?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, catosId);// 设置输入参数的值
                    cs.setString(3, month);// 设置输入参数的值
                    cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Driver> driverList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值


                    //  int i =1;
                    while (rs.next()) {//
                        Driver driver = new Driver();
                        driver.setCntr10(rs.getString("ONE"));
                        driver.setCntr20(rs.getString("TWO"));

                        driver.setCntr40(rs.getString("FOUR"));
                        driver.setCntr45(rs.getString("LARGE"));
                        driver.setDay(rs.getString("DAY"));
                        driver.setCntrSpe(rs.getString("SPE"));
                        driver.setDangerCntr(rs.getString("DG"));
                        driver.setQty(rs.getInt("QTY"));
                        driver.setCatosId(catosId);
                        driverList.add(driver);
                    }
                    rs.close();
                    // System.out.println(truckList);
                    return driverList;
                });
        return resultList;
    }

    //1. 中控查件
    public List<Driver> findWorkerQty(String catosId, String month) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_workload(?,?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.setString(2, catosId);// 设置输入参数的值
                    cs.setString(3, month);// 设置输入参数的值
                    cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Driver> driverList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值


                    //  int i =1;
                    while (rs.next()) {//
                        Driver driver = new Driver();

                        driver.setDay(rs.getString("DAY"));

                        driver.setQty(rs.getInt("QTY"));
                        driver.setCatosId(catosId);
                        driverList.add(driver);
                    }
                    rs.close();
                    // System.out.println(truckList);
                    return driverList;
                });
        return resultList;
    }
    //司机箱量存储过程通用查询 code

//3. 单个过机信息查询
//4. 危险品查询(按场位)

    public List<Driver> findDriverBoth(String code, String catosId, String month) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_workload(?,?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, code);// 设置输入参数的值
                    cs.setString(2, catosId);// 设置输入参数的值
                    cs.setString(3, month);// 设置输入参数的值
                    cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Driver> driverList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值


                    //  int i =1;
                    while (rs.next()) {//
                        Driver driver = new Driver();
                        driver.setCntr20(rs.getString("TQTY"));
                        driver.setCntr40(rs.getString("FQTY"));
                        driver.setDay(rs.getString("DAY"));
                        driver.setQty(rs.getInt("QTY"));
                        driver.setCatosId(catosId);
                        driverList.add(driver);
                    }
                    rs.close();
                    // System.out.println(truckList);
                    return driverList;
                });
        return resultList;
    }

    //nct司机查件
    public List<Driver> findNctDriverQty(String workId, String month) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call NCT_PUBLIC_SLT_WORKLOAD(?,?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, workId);// 设置输入参数的值
                    cs.setString(3, month);// 设置输入参数的值
                    cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Driver> driverList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值


                    //  int i =1;
                    while (rs.next()) {//
                        Driver driver = new Driver();
                        driver.setDay(rs.getString("TIM"));
                        driver.setCntr20(rs.getString("20E"));
                        driver.setCntr40(rs.getString("40E"));
                        driver.setCntr20F(rs.getString("20F"));
                        driver.setCntr40F(rs.getString("40F"));
                        driver.setQtyNct(rs.getDouble("QTY"));
                        driver.setCatosId(workId);
                        driverList.add(driver);
                    }
                    rs.close();
                    // System.out.println(truckList);
                    return driverList;
                });
        return resultList;
    }

    //nct拖车查件
    public List<Driver> findNctTruckQty(String workId, String month) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call NCT_PUBLIC_SLT_WORKLOAD(?,?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.setString(2, workId);// 设置输入参数的值
                    cs.setString(3, month);// 设置输入参数的值
                    cs.registerOutParameter(4, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Driver> driverList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(4);// 获取游标一行的值


                    //  int i =1;
                    while (rs.next()) {//
                        Driver driver = new Driver();
                        driver.setDay(rs.getString("TIM"));
                        driver.setCntr20(rs.getString("20E"));
                        driver.setCntr40(rs.getString("40E"));
                        driver.setCntr20F(rs.getString("20F"));
                        driver.setCntr40F(rs.getString("40F"));
                        driver.setQtyNct(rs.getDouble("QTY"));
                        driver.setCatosId(workId);
                        driverList.add(driver);
                    }
                    rs.close();
                    // System.out.println(truckList);
                    return driverList;
                });
        return resultList;
    }
}
