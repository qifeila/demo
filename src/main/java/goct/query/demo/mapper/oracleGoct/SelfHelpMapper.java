package goct.query.demo.mapper.oracleGoct;

import goct.query.demo.model.SelfHelp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SelfHelpMapper {
    List<SelfHelp> getHelpByKey(@Param("question") String question);
}
