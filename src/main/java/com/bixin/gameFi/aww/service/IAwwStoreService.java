package com.bixin.gameFi.aww.service;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;

import java.util.List;
import java.util.Map;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
public interface IAwwStoreService {

    int insert(AwwMatchRecords record);

    List<AwwMatchRecords> selectByPages(boolean predicateNextPage, String sellAddress, String buyAddress,
                                        long pageSize, long pageNum, int sort);

}
