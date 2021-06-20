package goct.query.demo.mapper.oracle91;

import goct.query.demo.model.Inventory;
import goct.query.demo.model.Truck;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

@Configuration
public class InventoryDAO {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    private DruidDataSource dataSource;

    private Connection connection;

//数据源配置
  /*  public InventoryDAO() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:sqlserver://10.1.0.122:1433;DatabaseName=ePortal");
            dataSource.setUsername("eportal");
            dataSource.setPassword("eportal!@#");
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        }
        connection = dataSource.getConnection();
    }*/

    //数据源配置
    public void getDateSource() throws SQLException {
        if ((dataSource == null) || dataSource.isClosed()) {
            dataSource = new DruidDataSource();
            dataSource.setUrl("jdbc:sqlserver://10.1.0.122:1433;DatabaseName=ePortal");
            dataSource.setUsername("eportal");
            dataSource.setPassword("eportal!@#");
            dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = dataSource.getConnection();
        }

    }


    //在场箱查询
    public List<Inventory> findInventoryInPort(String container) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_inventory(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, container);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Inventory> inventoryList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i = 1;
                    while (rs.next()) {//
                        Inventory inventory = new Inventory();
                        // Berth berth = new Berth();
                        inventory.setInventoryId(i);
                        i = i + 1;

                        inventory.setCntrNo(rs.getString("CNTR_NO"));
                        inventory.setFe(rs.getString("FE"));
                        inventory.setSztp(rs.getString("SZTP"));
                        inventory.setPod(rs.getString("POD"));
                        inventory.setPol(rs.getString("POL"));
                        if (!StringUtils.isEmpty(rs.getString("IX_CD")))
                            inventory.setIxCd(rs.getString("IX_CD"));
                        inventory.setTier(rs.getString("TIER"));
                        inventory.setPtnrCode(rs.getString("PTNR_CODE"));
                        inventory.setWgt(rs.getString("WGT"));
                        if (!StringUtils.isEmpty(rs.getString("BL_NO")))
                            inventory.setBlNo(rs.getString("BL_NO"));
                        inventory.setBookingNo(rs.getString("BOOKING_NO"));
                        if (!StringUtils.isEmpty(rs.getString("FLAG")))
                            inventory.setFlag(rs.getString("FLAG"));
                        if (!StringUtils.isEmpty(rs.getString("VGM_CHK")))
                            inventory.setVgmChk(rs.getString("VGM_CHK"));
                        //  inventory.setBookingNo(rs.getString("BOOKING_NO"));
                        if (!StringUtils.isEmpty(rs.getString("CIQ_CHK")))
                            inventory.setCiqChk(rs.getString("CIQ_CHK"));
                        // inventory.setBlNo(rs.getString("BL_NO"));
                        if (!StringUtils.isEmpty(rs.getString("THOLD_CHK")))
                            inventory.setTholdChk(rs.getString("THOLD_CHK"));
                        if (!StringUtils.isEmpty(rs.getString("TERMINAL")))
                            inventory.setTerminal(rs.getString("TERMINAL"));
                        if (!StringUtils.isEmpty(rs.getString("VSL_NM")))
                            inventory.setVslNm(rs.getString("VSL_NM"));
                        if (!StringUtils.isEmpty(rs.getString("IN_TMNL_DATE")))
                            inventory.setInTime(rs.getString("IN_TMNL_DATE"));

                        inventoryList.add(inventory);
                    }
                    rs.close();
                    //      System.out.println(inventoryList);
                    return inventoryList;
                });
        return resultList;
    }

    //可提吉柜数量查询
    public List<Inventory> findInventoryNumE(String ptnrCode) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_doc(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, ptnrCode);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Inventory> inventoryList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i = 1;
                    while (rs.next()) {//
                        Inventory inventory = new Inventory();
                        // Berth berth = new Berth();
                        inventory.setInventoryId(i);
                        i = i + 1;

                        inventory.setTotal(rs.getString("TOTAL"));

                        if (!StringUtils.isEmpty(rs.getString("STORAGE_CODE")))
                            inventory.setStorageCode(rs.getString("STORAGE_CODE"));
                        inventory.setPriCode(rs.getString("PRI_CODE"));
                        inventory.setPtnrCode(rs.getString("PTNR_CODE"));


                        if (!StringUtils.isEmpty(rs.getString("THOLD_CHK")))
                            inventory.setTholdChk(rs.getString("THOLD_CHK"));

                        inventoryList.add(inventory);
                    }
                    rs.close();
                    //      System.out.println(inventoryList);
                    return inventoryList;
                });
        return resultList;
    }

    //查验信息查询 单号122sqlserver
    public List<Inventory> findCheckInfo(String container) throws SQLException {
        return (List<Inventory>) jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call dbo.GOCT_PUBLIC_SLT_INVENTORY (?)}";
                CallableStatement cs = connection.prepareCall(storedProc);
                cs.setString(1, container);

                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException {
                List<Inventory> inventoryList = new ArrayList<>();
                ResultSet rs = cs.executeQuery();
                ResultSetMetaData rmd = rs.getMetaData();
                int columnCount = rmd.getColumnCount();
                System.out.println("columnCount: " + columnCount);
                int i = 1;
                while (rs.next()) {
                    Inventory inventory = new Inventory();
                    // Berth berth = new Berth();
                    inventory.setInventoryId(i);
                    i = i + 1;

                    inventory.setCntrNo(rs.getString("BlNo"));

                    inventory.setStatus(rs.getString("Status"));
                    inventory.setBlNo(rs.getString("ContainerNO"));

                    if (!StringUtils.isEmpty(rs.getString("requester")))
                        inventory.setRequester(rs.getString("requester"));
                    inventory.setSztp(rs.getString("SizeType"));
                    inventory.setPtnrCode(rs.getString("OPERATOR"));


                    if (!StringUtils.isEmpty(rs.getString("CargoType")))
                        inventory.setCargoType(rs.getString("CargoType"));

                    inventoryList.add(inventory);

                }
                rs.close();
                connection.close();
                dataSource.close();
                return inventoryList;

            }
        });
    }

    //查验信息查询 柜号122sqlserver
    public List<Inventory> getCheckInfo(String container) throws SQLException {
        return (List<Inventory>) jdbcTemplate.execute(new CallableStatementCreator() {
            @Override
            public CallableStatement createCallableStatement(Connection con) throws SQLException {
                String storedProc = "{call dbo.GOCT_PUBLIC_SLT_INVENTORY_CON (?)}";
                CallableStatement cs = connection.prepareCall(storedProc);
                cs.setString(1, container);

                return cs;
            }
        }, new CallableStatementCallback() {
            public Object doInCallableStatement(CallableStatement cs) throws SQLException {
                List<Inventory> inventoryList = new ArrayList<>();
                ResultSet rs = cs.executeQuery();
                ResultSetMetaData rmd = rs.getMetaData();
                int columnCount = rmd.getColumnCount();
                System.out.println("columnCount: " + columnCount);
                int i = 1;
                while (rs.next()) {
                    Inventory inventory = new Inventory();
                    // Berth berth = new Berth();
                    inventory.setInventoryId(i);
                    i = i + 1;

                    inventory.setCntrNo(rs.getString("ContainerNO"));

                    inventory.setOperationTime(rs.getString("OperationTime"));
                    inventory.setDescription(rs.getString("Description"));

                    if (!StringUtils.isEmpty(rs.getString("Origin")))
                        inventory.setOrigin(rs.getString("Origin"));

                    inventoryList.add(inventory);

                }
                rs.close();
                connection.close();
                dataSource.close();
                return inventoryList;

            }
        });
    }

    //过机信息查询 多个柜号
    public List<Inventory> findPassingInfo(String container) {
        // System.out.println(container);
        String[] a = container.split("\\.");//转义
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            stringBuilder.append("'");
            stringBuilder.append(a[i]);
            if (i != a.length - 1)
                stringBuilder.append("',");
            else
                stringBuilder.append("'");

        }
        // System.out.println("11111"+stringBuilder);
        String containers = stringBuilder.toString();
        //    System.out.println(containers);
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_inventory(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.setString(2, containers);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Inventory> inventoryList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i = 1;
                    while (rs.next()) {//
                        Inventory inventory = new Inventory();
                        // Berth berth = new Berth();
                        inventory.setInventoryId(i);
                        i = i + 1;

                        inventory.setCntrNo(rs.getString("CNTR_NO"));
                        inventory.setFe(rs.getString("FE"));

                        if (!StringUtils.isEmpty(rs.getString("BL_NO")))
                            inventory.setBlNo(rs.getString("BL_NO"));
                        if (!StringUtils.isEmpty(rs.getString("BOOKING_NO")))
                            inventory.setBookingNo(rs.getString("BOOKING_NO"));
                        if (!StringUtils.isEmpty(rs.getString("PASS_DATE")))
                            inventory.setPassDate(rs.getString("PASS_DATE"));


                        inventoryList.add(inventory);
                    }
                    rs.close();
                    //      System.out.println(inventoryList);
                    return inventoryList;
                });
        return resultList;
    }

    //过机信息查询 柜号 单号 预约号
    public List<Inventory> findPassingInfo1(String container) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_inventory(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "3");// 设置输入参数的值
                    cs.setString(2, container);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Inventory> inventoryList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值


                    int i = 1;
                    while (rs.next()) {//
                        Inventory inventory = new Inventory();
                        // Berth berth = new Berth();
                        inventory.setInventoryId(i);
                        i = i + 1;

                        inventory.setCntrNo(rs.getString("CNTR_NO"));
                        inventory.setFe(rs.getString("FE"));

                        if (!StringUtils.isEmpty(rs.getString("BL_NO")))
                            inventory.setBlNo(rs.getString("BL_NO"));
                        if (!StringUtils.isEmpty(rs.getString("BOOKING_NO")))
                            inventory.setBookingNo(rs.getString("BOOKING_NO"));
                        if (!StringUtils.isEmpty(rs.getString("PASS_DATE")))
                            inventory.setPassDate(rs.getString("PASS_DATE"));


                        inventoryList.add(inventory);
                    }
                    rs.close();
                    //     System.out.println(inventoryList);
                    return inventoryList;
                });
        return resultList;
    }

    //危险品查询 输入 柜号
    public List<Inventory> findDangerousInventory(String container) {
        PreparedStatement pst = null ;
        ResultSet rs = null;
        List<Inventory> inventoryList = new ArrayList<>();
        String container1 = container.toUpperCase();
        String sql = "SELECT TOP 1000 [CNTR_NO]\n" +
                "      ,[SZTP]\n" +
                "      ,[IMDG]\n" +
                "      ,[UNNO]\n" +
                "      ,[DANGER_NAME]\n" +
                "      ,[WGT]\n" +
                "      ,[IN_YARD_TIME]\n" +
                "      ,[POS]\n" +
                "  FROM [ePortal].[dbo].[GOCT_VIEW_DANGER_CONTAINER]\n" +
                "  where SUBSTRING(pos,1,2)=?  OR CNTR_NO=? ";

        try{
            pst=connection.prepareStatement(sql);
            pst.setString(1, container1);
            pst.setString(2, container1);

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
    }
    //inventoryKPI
    public List<Inventory> findInventoryKpi(){
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call GOCT_ETL_INVENTORY(?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    //cs.setString(2, container);// 设置输入参数的值
                    cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List<Inventory> inventoryList = new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值


                //    int i = 1;
                    while (rs.next()) {//
                        Inventory inventory = new Inventory();


                        inventory.setTerminal(rs.getString("TERMINAL"));
                        inventory.setKpi(rs.getString("KPI"));
                        inventory.setItem(rs.getString("ITEM"));
                        inventory.setQty(rs.getString("QTY"));

                       /* if (!StringUtils.isEmpty(rs.getString("ITEM")))
                            inventory.setBlNo(rs.getString("BL_NO"));*/


                        inventoryList.add(inventory);
                    }
                    rs.close();
                    //     System.out.println(inventoryList);
                    return inventoryList;
                });
        return resultList;

    }


}
