package goct.query.demo.mapper.oracleGoct;

import goct.query.demo.model.PreGate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
    public interface PreGateMapper {
        //插入记录
        void insertPreGate(PreGate preGate);
        //根据箱号查找coparn表无数据的pregate表数据,repair_time 降序
        List<PreGate> findByCntr(@Param("cntrNo")String cntrNo);
    }

