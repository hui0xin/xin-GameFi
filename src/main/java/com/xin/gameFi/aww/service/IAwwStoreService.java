package com.xin.gameFi.aww.service;

import com.xin.gameFi.aww.bean.DO.AwwMatchRecords;

import java.util.List;

/**
 * create  2021/12/9
 */
public interface IAwwStoreService {

    int insert(AwwMatchRecords record);

    List<AwwMatchRecords> selectByPages(boolean predicateNextPage, String sellAddress, String buyAddress,
                                        long pageSize, long pageNum, int sort);

}
