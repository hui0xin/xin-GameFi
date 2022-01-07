package com.bixin.gameFi.aww.service;

import com.bixin.gameFi.aww.bean.DO.AwwMarket;
import com.bixin.gameFi.aww.core.wrapDDL.AwwMarketDDL;

import java.util.List;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
public interface IAwwMarketService {

    int insert(AwwMarket record);

    List<AwwMarket> selectAll();

    int deleteAll();

    int deleteById(List<Long> id);


    int updateById(AwwMarket record);

    List<AwwMarket> selectByPages(boolean predicateNextPage,
                                  String owner,
                                  Long startPrice,
                                  Long endPrice,
                                  List<Integer> raritys,
                                  Long pageSize,
                                  Long pageNum,
                                  int sort);

}
