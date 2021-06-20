package goct.query.demo.mapper.oracle91;

import goct.query.demo.model.Berth;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Configuration
public class BerthDAO {
    @Autowired
    @Qualifier("ds1JdbcTemplate")
    private JdbcTemplate jdbcTemplate;

   //10天船期
    public List<Berth> findShipAll() {
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_berthplan(?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "1");// 设置输入参数的值
                    cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Berth> berthList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//
                        Berth berth = new Berth();
                        berth.setBerthId(i);
                        i=i+1;
                        berth.setVslCd(rs.getString("VSL_CD"));
                        berth.setCallYear(rs.getString("CALL_YEAR"));
                        berth.setCallSeq(rs.getString("CALL_SEQ"));
                        berth.setVslName(rs.getString("VSL_NM"));
                        berth.setOldVslName(rs.getString("OLD_VSL_NM"));
                        berth.setInLane(rs.getString("IN_LANE"));
                        berth.setOutLane(rs.getString("OUT_LANE"));
                        berth.setInVoy(rs.getString("IN_VOY"));
                        berth.setOutVoy(rs.getString("OUT_VOY"));
                        berth.setEta(rs.getString("ETA"));
                        berth.setEtb(rs.getString("ETB"));
                        if (rs.getString("YARD_CLOSE") != null){
                            berth.setYardClose(rs.getString("YARD_CLOSE"));}
                        berth.setInmarsat(rs.getString("INMARSAT"));
                        berth.setTerminal(rs.getString("TERMINAL"));
                        berthList.add(berth); }
                    rs.close();
                 //   System.out.println(berthList);
                    return berthList;
                });
        return resultList;
    }
    //当前在港轮班
    public List<Berth> findShipInPort(){
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_berthplan(?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "2");// 设置输入参数的值
                    cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Berth> berthList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;
                  //  Berth berth = new Berth();
                    int i =1;
                    while (rs.next()) {//
                        Berth berth = new Berth();
                        berth.setBerthId(i);
                        i=i+1;
                        berth.setVslCd(rs.getString("VSL_CD"));
                        berth.setCallYear(rs.getString("CALL_YEAR"));
                        berth.setCallSeq(rs.getString("CALL_SEQ"));
                        berth.setVslName(rs.getString("VSL_NM"));
                        berth.setOldVslName(rs.getString("OLD_VSL_NM"));
                        berth.setInLane(rs.getString("IN_LANE"));
                        berth.setOutLane(rs.getString("OUT_LANE"));
                        berth.setInVoy(rs.getString("IN_VOY"));
                        berth.setOutVoy(rs.getString("OUT_VOY"));
                        berth.setEtd(rs.getString("ETD"));
                        berth.setAtb(rs.getString("ATB"));
                        berth.setStatus(rs.getString("STATUS"));
                        berth.setTerminal(rs.getString("TERMINAL"));
                        berthList.add(berth); }
                    rs.close();
           //         System.out.println(berthList);
                    return berthList;
                });
        return resultList;

    }
    //当前在港驳船
    public List<Berth> findBargeInPort(){
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_berthplan(?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "3");// 设置输入参数的值
                    cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Berth> berthList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;
                    //Berth berth = new Berth();
                    int i =1;
                    while (rs.next()) {//
                        Berth berth = new Berth();
                        berth.setBerthId(i);
                        i=i+1;
                        berth.setStatus(rs.getString("STATUS"));
                        berth.setVslCd(rs.getString("VSL_CD"));
                        berth.setCallYear(rs.getString("CALL_YEAR"));
                        berth.setCallSeq(rs.getString("CALL_SEQ"));
                        berth.setVslName(rs.getString("VSL_NM"));
                        berth.setOldVslName(rs.getString("OLD_VSL_NM"));
                        berth.setPtnrCode(rs.getString("PTNR_CODE"));
                        berth.setInLane(rs.getString("IN_LANE"));
                        berth.setOutLane(rs.getString("OUT_LANE"));
                      //if(rs.getString("IN_VOY")!= null && rs.getString("IN_VOY")!= "")
                        if(!StringUtils.isEmpty(rs.getString("IN_VOY")))
                        berth.setInVoy(rs.getString("IN_VOY"));
                        if(rs.getString("OUT_VOY")!= null && rs.getString("OUT_VOY")!= "")
                            berth.setOutVoy(rs.getString("OUT_VOY"));
                        berth.setBerthNo(rs.getString("BERTH_NO"));
                        berth.setFromBitt(rs.getString("FROM_BITT"));
                        berth.setToBitt(rs.getString("TO_BITT"));
                        berth.setEtd(rs.getString("ETD"));
                        berth.setAta(rs.getString("ATA"));
                        if(rs.getString("ATW")!= null)
                            berth.setAtw(rs.getString("ATW"));
                        if(rs.getString("ATC")!= null)
                            berth.setAtc("");
                        berth.setTotal(rs.getInt("TOTAL"));
                        berth.setRemainQty(rs.getInt("REMAIN_QTY"));
                        berth.setLoQty(rs.getInt("LO_QTY"));
                        berth.setDsQty(rs.getInt("DS_QTY"));
                        berth.setRatio(rs.getDouble("RATIO"));

                        berth.setVqvh(rs.getDouble("VQVH"));
                        berth.setTerminal(rs.getString("TERMINAL"));

                        berthList.add(berth); }
                    rs.close();
                    System.out.println("查询成功");
                    return berthList;
                });
        return resultList;
    }
    //当前离港班轮
    public List<Berth> findShipDepartPort(){
        List resultList = (List) jdbcTemplate.execute(
                con -> {
                    String storedProc = "{call goct_public_slt_berthplan(?,?)}";// 调用的sql
                    CallableStatement cs = con.prepareCall(storedProc);
                    cs.setString(1, "4");// 设置输入参数的值
                    cs.registerOutParameter(2, OracleTypes.CURSOR);// 注册输出参数的类型
                    return cs;
                }, (CallableStatementCallback) cs -> {
                    List <Berth> berthList= new ArrayList();
                    cs.execute();
                    ResultSet rs = (ResultSet) cs.getObject(2);// 获取游标一行的值
                    //List<Berth>  berthList = (List<Berth>) rs;

                    int i =1;
                    while (rs.next()) {//
                        Berth berth = new Berth();
                        berth.setBerthId(i);
                        i=i+1;
                        berth.setVslCd(rs.getString("VSL_CD"));
                        berth.setCallYear(rs.getString("CALL_YEAR"));
                        berth.setCallSeq(rs.getString("CALL_SEQ"));
                        berth.setVslName(rs.getString("VSL_NM"));
                        berth.setOldVslName(rs.getString("OLD_VSL_NM"));
                      //  berth.setPtnrCode(rs.getString("PTNR_CODE"));
                        berth.setInLane(rs.getString("IN_LANE"));
                        berth.setOutLane(rs.getString("OUT_LANE"));
                        berth.setInVoy(rs.getString("IN_VOY"));
                        berth.setOutVoy(rs.getString("OUT_VOY"));

                        berth.setAtb(rs.getString("ATB"));
                        berth.setAtw(rs.getString("ATW"));
                        berth.setAtd(rs.getString("ATD"));
                        berth.setTerminal(rs.getString("TERMINAL"));

                        berthList.add(berth); }
                    rs.close();
           //         System.out.println(berthList);
                    return berthList;
                });
        return resultList;

    }
    //过滤
    public List<Berth> findShipTerminal( List<Berth> berthList,String terminal){
        List<Berth> terminalList = new ArrayList<>();

        for (int i= 0 ;i<berthList.size();i++){

            if(berthList.get(i).getTerminal().equals(terminal) ) {
                terminalList.add(berthList.get(i));
            }

        }
        return terminalList;
    }

}
