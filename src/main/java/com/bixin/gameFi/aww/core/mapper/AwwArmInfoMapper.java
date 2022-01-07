package com.bixin.gameFi.aww.core.mapper;

import com.bixin.gameFi.aww.bean.DO.AwwArmInfo;
import com.bixin.gameFi.aww.core.wrapDDL.AwwArmInfoDDL;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AwwArmInfoMapper {
    long countByDDL(AwwArmInfoDDL DDL);

    int deleteByDDL(AwwArmInfoDDL DDL);

    int deleteByPrimaryKey(Long id);

    int insert(AwwArmInfo record);

    int insertSelective(AwwArmInfo record);

    List<AwwArmInfo> selectByDDLWithBLOBs(AwwArmInfoDDL DDL);

    List<AwwArmInfo> selectByDDL(AwwArmInfoDDL DDL);

    AwwArmInfo selectByPrimaryKey(Long id);

    int updateByDDLSelective(@Param("record") AwwArmInfo record, @Param("DDL") AwwArmInfoDDL DDL);

    int updateByDDLWithBLOBs(@Param("record") AwwArmInfo record, @Param("DDL") AwwArmInfoDDL DDL);

    int updateByDDL(@Param("record") AwwArmInfo record, @Param("DDL") AwwArmInfoDDL DDL);

    int updateByPrimaryKeySelective(AwwArmInfo record);

    int updateByPrimaryKeyWithBLOBs(AwwArmInfo record);

    int updateByPrimaryKey(AwwArmInfo record);
}