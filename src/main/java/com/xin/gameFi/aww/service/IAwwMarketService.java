package com.xin.gameFi.aww.service;

import com.xin.gameFi.aww.bean.DO.AwwMarket;

import java.util.List;

/**
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
