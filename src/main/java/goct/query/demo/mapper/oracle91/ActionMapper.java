package goct.query.demo.mapper.oracle91;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ActionMapper {
     void insertAction(@Param("actionName") String actionName, @Param("time")String time,
                     @Param("frequency")Integer frequency);
    void insertActionSim(@Param("actionName") String actionName, @Param("time")String time);
    void updateAction();
    Integer getActionId();
    Integer getLastActionFreq(@Param("actionName") String actionName);
}
