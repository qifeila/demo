package goct.query.demo.mapper.oracleGoct;

import goct.query.demo.model.GateEir;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GateEirMapper {
    //根据箱号查找所有重出记录
    List<GateEir> findByCntr(@Param("cntrNo") String cntrNo);
}

