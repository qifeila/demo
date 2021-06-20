package goct.query.demo.mapper.oracle91;

import goct.query.demo.model.Gate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface GateMapper {
    void GetTestPro();
    List<Gate> getTruckTime ();
    List<Gate> getTruckNum ();
    List<Gate> getThroughput ();
    List<Gate> getYesterday(String date);
    /**
     *柜进出数据
     * @param date  yesterday
     * @return gatelist
     */
    List<Gate> getYesterdayFE(String date);//暂时没有用到

    List<Gate> getTruckTimeByYear (String year);
    List<Gate> getTruckNumByYear (String year);
    List<Gate> getThroughputByYear (String year);

    /**
     *柜进出数据
     * 每日都有
     * @return gatelist
     */
    List<Gate> getFin ();
    List<Gate> getEin ();
    List<Gate> getFout ();
    List<Gate> getEout ();

    /**
     *柜进出数据
     * @param year 今年
     * @return
     */
    List<Gate> getFinByYear (String year);
    List<Gate> getEinByYear (String year);
    List<Gate> getFoutByYear (String year);
    List<Gate> getEoutByYear (String year);



}
