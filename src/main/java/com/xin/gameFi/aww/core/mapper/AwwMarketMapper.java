package com.xin.gameFi.aww.core.mapper;

import com.xin.gameFi.aww.bean.DO.AwwMarket;
import com.xin.gameFi.aww.core.wrapDDL.AwwMarketDDL;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface AwwMarketMapper {
    long countByDDL(AwwMarketDDL DDL);

    int deleteByDDL(AwwMarketDDL DDL);

    int deleteByPrimaryKey(Long id);

    int insert(AwwMarket record);

    int insertSelective(AwwMarket record);

    List<AwwMarket> selectByDDL(AwwMarketDDL DDL);

    AwwMarket selectByPrimaryKey(Long id);

    int updateByDDLSelective(@Param("record") AwwMarket record, @Param("DDL") AwwMarketDDL DDL);

    int updateByDDL(@Param("record") AwwMarket record, @Param("DDL") AwwMarketDDL DDL);

    int updateByPrimaryKeySelective(AwwMarket record);

    int updateByPrimaryKey(AwwMarket record);

    List<AwwMarket> selectByPages(Map<String, Object> paramMap);

}