package com.bixin.gameFi.aww.core.mapper;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.bixin.gameFi.aww.core.wrapDDL.AwwMatchRecordsDDL;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface AwwMatchRecordsMapper {
    long countByDDL(AwwMatchRecordsDDL DDL);

    int deleteByDDL(AwwMatchRecordsDDL DDL);

    int deleteByPrimaryKey(Long id);

    int insert(AwwMatchRecords record);

    int insertSelective(AwwMatchRecords record);

    List<AwwMatchRecords> selectByDDL(AwwMatchRecordsDDL DDL);

    AwwMatchRecords selectByPrimaryKey(Long id);

    int updateByDDLSelective(@Param("record") AwwMatchRecords record, @Param("DDL") AwwMatchRecordsDDL DDL);

    int updateByDDL(@Param("record") AwwMatchRecords record, @Param("DDL") AwwMatchRecordsDDL DDL);

    int updateByPrimaryKeySelective(AwwMatchRecords record);

    int updateByPrimaryKey(AwwMatchRecords record);

    List<AwwMatchRecords> selectByPages(Map<String, Object> paramMap);

}