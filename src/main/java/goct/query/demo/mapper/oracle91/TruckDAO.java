package goct.query.demo.mapper.oracle91;

import com.alibaba.druid.pool.DruidDataSource;
import goct.query.demo.model.Berth;
import goct.query.demo.model.Inventory;
import goct.query.demo.model.Truck;
import oracle.jdbc.internal.OracleTypes;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class TruckDAO {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private DruidDataSource dataSource;

    private Connection connection;

    //数据源配置
   /* public TruckDAO() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:sqlserver://10.1.0.159:1433;DatabaseName=lanesettings");
            dataSource.setUsername("sa");
            dataSource.setPassword("pfkj2@!8");
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        connection = dataSource.getConnection();
    }*/

    //数据源配置
    public void getDateSource() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:sqlserver://10.1.0.159:1433;DatabaseName=lanesettings");
            dataSource.setUsername("sa");
            dataSource.setPassword("pfkj2@!81");
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //启动连接
            connection = dataSource.getConnection();
        }

    }

    //1. 当前作业查询
    public List<Truck> findTruckPlace(String headNo){
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, headNo);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Truck> truckList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i =1;
                    while (rs.next()) {//
                        Truck truck = new Truck();
                       // Berth berth = new Berth();
                        truck.setTruckId(i);
                        i=i+1;

                        truck.setHeadNo(rs.getString("HEAD_NO"));
                        truck.setPriCode(rs.getString("PRI_CODE"));
                        truck.setGjobType(rs.getString("GJOB_TYPE"));
                        truck.setYequNo(rs.getString("YEQU_NO"));
                        truck.setCntrNo(rs.getString("CNTR_NO"));
                        truck.setPosition(rs.getString("POSITION"));
                        truck.setGpassDate(rs.getString("GPASS_DATE"));
                        if(!StringUtils.isEmpty(rs.getString("YARD_TIME")))
                            truck.setYardTime(rs.getString("YARD_TIME"));
                        if(!StringUtils.isEmpty(rs.getString("BOOKING_NO")))
                            truck.setBookingNo(rs.getString("BOOKING_NO"));
                        truck.setGisPosition(rs.getString("GISPOSITION"));
                        if(!StringUtils.isEmpty(rs.getString("BL_NO")))
                            truck.setBlNo(rs.getString("BL_NO"));
                        if(!StringUtils.isEmpty(rs.getString("CARGO_TYPE")))
                            truck.setCargoType(rs.getString("CARGO_TYPE"));
                        truckList.add(truck); }
                    rs.close();
                   // System.out.println(truckList);
                    return truckList;
                });
        return resultList;
    }

     //        2. 查询最后一次拖车周转时间
     public List<Truck> findTruckTime(String headNo){
         List resultList = (List) jdbcTemplate.execute(
                 con -> {
                     String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                     CallableStatement cs = con.prepareCall(storedProc);
                     cs.setString(1, "2");// 设置输入参数的值
                     cs.setString(2, headNo);// 设置输入参数的值
                     cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                     return cs;
                 }, (CallableStatementCallback) cs -> {
                     List <Truck> truckList= new ArrayList();
                     cs.execute();
                     ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值

                     int i =1;
                     while (rs.next()) {//
                         Truck truck = new Truck();
                         // Berth berth = new Berth();
                         truck.setTruckId(i);
                         i=i+1;
                         truck.setHeadNo(rs.getString("HEAD_NO"));
                         truck.setGpassDate(rs.getString("GPASS_DATE"));
                         truck.setOutDate(rs.getString("OUT_DATE"));

                         truckList.add(truck); }
                     rs.close();
                  //   System.out.println(truckList);
                     return truckList;
                 });
         return resultList;

     }
     //        3. 查询拖车提柜的柜号与单号对应信息
     public List<Truck> findTruckContainer(String container){
         List resultList = (List) jdbcTemplate.execute(
                 con -> {
                     String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                     CallableStatement cs = con.prepareCall(storedProc);
                     cs.setString(1, "3");// 设置输入参数的值
                     cs.setString(2, container);// 设置输入参数的值
                     cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                     return cs;
                 }, (CallableStatementCallback) cs -> {
                     List <Truck> truckList= new ArrayList();
                     cs.execute();
                     ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                     int i =1;
                     while (rs.next()) {//
                         Truck truck = new Truck();
                         // Berth berth = new Berth();
                         truck.setTruckId(i);
                         i=i+1;
                         truck.setTagNo(rs.getString("TAG_NO"));
                         truck.setSztp2(rs.getString("SZTP2"));
                         if(!StringUtils.isEmpty(rs.getString("BOOKING_NO")))
                             truck.setBookingNo(rs.getString("BOOKING_NO"));
                         if(!StringUtils.isEmpty(rs.getString("VSL_CD")))
                             truck.setVslCd(rs.getString("VSL_CD"));
                         if(!StringUtils.isEmpty(rs.getString("CNTR_NO")))
                             truck.setCntrNo(rs.getString("CNTR_NO"));
                         if(!StringUtils.isEmpty(rs.getString("PTNR_CODE")))
                             truck.setPtnrCode(rs.getString("PTNR_CODE"));


                         truckList.add(truck); }
                     rs.close();
               //      System.out.println(truckList);
                     return truckList;
                 });
         return resultList;
     }
      //       4. 基于提单号查找对应的拖车作业信息
      public List<Truck> findTruckBLNO(String BLNO){
          List resultList = (List) jdbcTemplate.execute(
                  con -> {
                      String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                      CallableStatement cs = con.prepareCall(storedProc);
                      cs.setString(1, "4");// 设置输入参数的值
                      cs.setString(2, BLNO);// 设置输入参数的值
                      cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                      return cs;
                  }, (CallableStatementCallback) cs -> {
                      List <Truck> truckList= new ArrayList();
                      cs.execute();
                      ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值

                      int i =1;
                      while (rs.next()) {//
                          Truck truck = new Truck();
                          // Berth berth = new Berth();
                          truck.setTruckId(i);
                          i=i+1;
                          truck.setHeadNo(rs.getString("HEAD_NO"));
                          if(!StringUtils.isEmpty(rs.getString("CNTR_NO")))
                              truck.setCntrNo(rs.getString("CNTR_NO"));
                          truck.setGpassDate(rs.getString("GPASS_DATE"));
                          truck.setOutDate(rs.getString("OUT_DATE"));
                          truck.setSicNo(rs.getString("SIC_NO"));

                          truckList.add(truck); }
                      rs.close();
                //      System.out.println(truckList);
                      return truckList;
                  });
          return resultList;

      }
     //        5. 查找三个月的集装箱进出闸记录
     public List<Truck> findContainerHis(String container){
         List resultList = (List) jdbcTemplate.execute(
                 con -> {
                     String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                     CallableStatement cs = con.prepareCall(storedProc);
                     cs.setString(1, "5");// 设置输入参数的值
                     cs.setString(2, container);// 设置输入参数的值
                     cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                     return cs;
                 }, (CallableStatementCallback) cs -> {
                     List <Truck> truckList= new ArrayList();
                     cs.execute();
                     ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                     int i =1;
                     while (rs.next()) {//
                         Truck truck = new Truck();
                         // Berth berth = new Berth();
                         truck.setTruckId(i);
                         i=i+1;
                         truck.setHeadNo(rs.getString("HEAD_NO"));
                         truck.setCntrNo(rs.getString("CNTR_NO"));
                         truck.setGpassDate(rs.getString("GPASS_DATE"));
                         truck.setOutDate(rs.getString("OUT_DATE"));

                         truckList.add(truck); }
                     rs.close();
               //      System.out.println(truckList);
                     return truckList;
                 });
         return resultList;
     }
      //       6. 基于月份查找车辆的作业记录
      public List<Truck> findTruckHis( String headNo, String time){
         String value = time+headNo;
         //this.getDataSource();
          List resultList = (List) jdbcTemplate.execute(
                  con -> {
                      String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                      CallableStatement cs = con.prepareCall(storedProc);
                      cs.setString(1, "6");// 设置输入参数的值
                      cs.setString(2, value);// 设置输入参数的值
                      cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                      return cs;
                  }, (CallableStatementCallback) cs -> {
                      List <Truck> truckList= new ArrayList();
                      cs.execute();
                      ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                      int i =1;
                      while (rs.next()) {//
                          Truck truck = new Truck();
                          // Berth berth = new Berth();
                          truck.setTruckId(i);
                          i=i+1;
                          truck.setHeadNo(rs.getString("HEAD_NO"));
                          truck.setCntrNo(rs.getString("CNTR_NO"));
                          truck.setGpassDate(rs.getString("GPASS_DATE"));
                          truck.setOutDate(rs.getString("OUT_DATE"));
                          truck.setGjobType(rs.getString("GJOB_TYPE"));


                          truckList.add(truck); }
                      rs.close();
               //       System.out.println(truckList);
                      return truckList;
                  });
          return resultList;
      }
      //         7 基于月份查找车辆的作业记录 159sqlserver
      public List<Truck> findTruckData( String headNo){
        return (List<Truck>) jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call dbo.GOCT_PUBLIC_SLT_GATE (?)}";
                CallableStatement cs = connection.prepareCall(storedProc);
                cs.setString(1, headNo);

                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException {
                List<Truck> truckList = new ArrayList<>();
                ResultSet rs = cs.executeQuery();
                ResultSetMetaData rmd = rs.getMetaData();
               int columnCount = rmd.getColumnCount();
             //   System.out.println("columnCount: " + columnCount);
                int i =1;
                while (rs.next()) {
                    Truck truck = new Truck();
                    // Berth berth = new Berth();
                    truck.setTruckId(i);
                    i=i+1;
                    truck.setHeadNo(rs.getString("truckNumber"));
                    truck.setTruckBody(rs.getString("TRUCKBODY"));
                    truck.setCompanyName(rs.getString("COMPANYNAME"));
                    truck.setHeadType(rs.getString("HEADTYPE"));
                    truck.setExpiryDate(rs.getString("EXPIRYDATE"));
                    if(!StringUtils.isEmpty(rs.getString("REASON")))
                        truck.setReason(rs.getString("REASON"));


                    truckList.add(truck);

                }
                rs.close();
                connection.close();
                dataSource.close();
                //  connection.close();
                return truckList;

            }
        });
    }
    // 8  外拖超长指令查询
    public List<Truck> findTruckLong( ){
        //String value = time+headNo;
        //this.getDataSource();
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_gate(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "7");// 设置输入参数的值
                    cs.setString(2, "");// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Truck> truckList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i =1;
                    while (rs.next()) {//
                        Truck truck = new Truck();
                        // Berth berth = new Berth();
                        truck.setTruckId(i);
                        i=i+1;
                        truck.setHeadNo(rs.getString("HEAD_NO"));
                        truck.setFe(rs.getString("FE"));
                        truck.setGpassDate(rs.getString("GPASS_DATE"));
                        truck.setPosition(rs.getString("POSITION"));
                        truck.setYequNo(rs.getString("YEQU_NO"));
                        truck.setGjobType(rs.getString("GJOB_TYPE"));
                        truck.setDifftime(rs.getInt("DIFFTIME"));
                       // truck.setGjobType(rs.getString("GJOB_TYPE"));

                        truckList.add(truck); }
                    rs.close();
                    //       System.out.println(truckList);
                    return truckList;
                });
        return resultList;
    }

}
