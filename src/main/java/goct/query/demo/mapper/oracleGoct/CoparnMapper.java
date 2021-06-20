package goct.query.demo.mapper.oracleGoct;

import goct.query.demo.model.Coparn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface CoparnMapper {
    //根据主键查找
    Coparn findByPk(@Param("odr_no")String odr_no);
    //根据箱号查找未进闸外修箱,used_date is null，FE='E',SIC_NO is not null
    List<Coparn> findByCntr(@Param("cntrNo")String cntrNo);
    //更新,根据主键
    void updateCopar(@Param("odr_no")String odr_no,@Param("repair_state")String repair_state,
                     @Param("repairer")String repairer,@Param("repair_remark")String repair_remark,
                     @Param("repair_time")Date repair_time);
}
