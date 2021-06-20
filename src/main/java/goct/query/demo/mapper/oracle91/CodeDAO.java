package goct.query.demo.mapper.oracle91;

import goct.query.demo.model.Berth;
import goct.query.demo.model.Code;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CodeDAO {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    //箱属代码 输入条件：中文名字
    public List<Code> findPtnrCode(String engSnm) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call GOCT_PUBLIC_SLT_CODE(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.setString(2, engSnm);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Code> codeList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//TERMINAL
                        Code code = new Code();
                        code.setEngSnm(rs.getString("ENG_SNM"));
                        code.setPtnrCode(rs.getString("PTNR_CODE"));
                        code.setTerminal(rs.getString("TERMINAL"));

                        codeList.add(code); }
                    rs.close();
                    //   System.out.println(berthList);
                    return codeList;
                });
        return resultList;
    }
    //用箱规则 输入条件：英文代码箱属
    public List<Code> cntrRule(String ptnrCode) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call GOCT_PUBLIC_SLT_CODE(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.setString(2, ptnrCode);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Code> codeList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//TERMINAL
                        Code code = new Code();
                        code.setPtnrCode(rs.getString("PTNR_CODE"));
                        code.setGoctCode(rs.getString("GOCT_CODE"));
                        code.setDefine(rs.getString("DEFINE"));
                        code.setRemark(rs.getString("REMARK"));
                        code.setTerminal(rs.getString("TERMINAL"));
                        codeList.add(code); }
                    rs.close();
                    //   System.out.println(berthList);
                    return codeList;
                });
        return resultList;
    }

    //港口代码 输入条件：国家代码或港口代码或港口名称
    public List<Code> portCode(String portName) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call GOCT_PUBLIC_SLT_CODE(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "3");// 设置输入参数的值
                    cs.setString(2, portName);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Code> codeList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//TERMINAL
                        Code code = new Code();
                        code.setPort(rs.getString("PORT"));
                        code.setPortName(rs.getString("PORT_NM"));
                        code.setTerminal(rs.getString("TERMINAL"));
                        codeList.add(code); }
                    rs.close();
                    //   System.out.println(berthList);
                    return codeList;
                });
        return resultList;
    }

    //危险品代码 输入条件： 危险品代码或类别
    public List<Code> dangerCode(String unno) {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call GOCT_PUBLIC_SLT_CODE(?,?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "4");// 设置输入参数的值
                    cs.setString(2, unno);// 设置输入参数的值
                    cs.registerOutParameter(3, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Code> codeList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(3);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//TERMINAL
                        Code code = new Code();
                        code.setDangerClass(rs.getString("CLASS"));
                        code.setUnno(rs.getString("UNNO"));
                        code.setProperShipName(rs.getString("PROPER_SHIP_NM"));
                        code.setStowageSeg(rs.getString("STOWAGE_SEG"));
                        code.setPropertyObservation(rs.getString("PROPERTY_OBSERVATION"));
                        codeList.add(code); }
                    rs.close();
                    //   System.out.println(berthList);
                    return codeList;
                });
        return resultList;
    }


}
