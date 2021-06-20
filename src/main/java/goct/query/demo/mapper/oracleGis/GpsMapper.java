package goct.query.demo.mapper.oracleGis;

import goct.query.demo.model.GpsDeviceLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GpsMapper {
    //查找
    List<GpsDeviceLog> findByCondition(@Param("deviceIp") String deviceIp,@Param("deviceName") String deviceName);

}
