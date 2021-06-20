package goct.query.demo.mapper.oracle91;

import com.alibaba.druid.pool.DruidDataSource;
import goct.query.demo.model.Berth;
import goct.query.demo.model.Edi;
import goct.query.demo.model.Truck;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class EdiDAO {
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
            dataSource.setUrl("jdbc:oracle:thin:@//10.1.0.47:1521/goctcxk");
            dataSource.setUsername("eportal");
            dataSource.setPassword("success");
            dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
            //启动连接
            connection = dataSource.getConnection();
        }

    }


    //重进EDI
    public List<Edi> findFInEdi(String soNo) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call pcatos.goct_public_slt_edi(?,?,?)}";// 调用的sql
                    CallableStatement cs = connection.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, soNo);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Edi> ediList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//
                        Edi edi = new Edi();
                        //   edi.setBerthId(i);
                        //  i=i+1;

                        if (rs.getString("VVD") != null){
                            edi.setVvd(rs.getString("VVD"));}
                        if (rs.getString("CALLSIGN") != null){
                            edi.setCallSign(rs.getString("CALLSIGN"));}
                        if (rs.getString("SO_NO") != null){
                            edi.setSoNo(rs.getString("SO_NO"));}
                        ediList.add(edi); }

                      //  berthList.add(edi); }
                    rs.close();
                    connection.close();
                    dataSource.close();
                    //   System.out.println(berthList);
                    return ediList;
                });
        return resultList;
    }



    //吉出重回EDI
    public List<Edi> findEOutEdi(String soNo) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call pcatos.goct_public_slt_edi(?,?,?)}";// 调用的sql
                    CallableStatement cs = connection.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.setString(2, soNo);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Edi> berthList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//
                        Edi edi = new Edi();
                        //   edi.setBerthId(i);
                        //  i=i+1;

                        if (rs.getString("SZTP") != null){
                            edi.setSztp(rs.getString("SZTP"));}
                        if (rs.getString("POD") != null){
                            edi.setPod(rs.getString("POD"));}
                        if (rs.getString("PTNR_CODE") != null){
                            edi.setPtnrCode(rs.getString("PTNR_CODE"));}
                        if (rs.getString("BOOK_QTY") != null){
                            edi.setBookQty(rs.getString("BOOK_QTY"));}
                        if (rs.getString("FPOD") != null){
                            edi.setFpod(rs.getString("FPOD"));}
                        if (rs.getString("OLD_VSL_NM") != null){
                            edi.setOldVslName(rs.getString("OLD_VSL_NM"));}
                        if (rs.getString("OUT_VOY") != null){
                            edi.setOutVoy(rs.getString("OUT_VOY"));}

                            berthList.add(edi); }
                    rs.close();
                    connection.close();
                    dataSource.close();
                    //   System.out.println(berthList);
                    return berthList;
                });
        return resultList;
    }

    //新提吉EDI，中间库
    public List<Edi> findEOutEdiNew(String soNo) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_doc(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "7");// 设置输入参数的值
                    cs.setString(2, soNo);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Edi> lstEdi= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    while (rs.next()) {
                        Edi edi = new Edi();
                        if (rs.getString("ISO_CODE") != null){
                            edi.setIso_code(rs.getString("ISO_CODE"));}
                        if (rs.getString("OPR") != null){
                            edi.setOpr(rs.getString("Opr"));}
                        if (rs.getString("CANCEL_STATUS") != null){
                            edi.setCancel_status(rs.getString("CANCEL_STATUS"));}
                        lstEdi.add(edi); }
                    rs.close();
                    cs.close();
                    if (dataSource!=null) {
                        dataSource.close();
                    }
                    return lstEdi;
                });
        return resultList;
    }

}
