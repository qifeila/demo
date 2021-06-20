package goct.query.demo.mapper.oracleGoct;

import goct.query.demo.model.CntrInAndOut;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @auther wmm
 * @date 2020/11/26 15:55
 */

@Mapper
public interface CntrInAndOutMapper {

     List<CntrInAndOut> findCntrInAndOut2_3();
     List<CntrInAndOut> findCntrInAndOut3_2();
}
