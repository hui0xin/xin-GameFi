package com.bixin.gameFi.aww.service.Impl;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.bixin.gameFi.aww.core.mapper.AwwMatchRecordsMapper;
import com.bixin.gameFi.aww.service.IAwwStoreService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
@Service
public class AwwStoreImpl implements IAwwStoreService {

    @Resource
    AwwMatchRecordsMapper awwMatchRecordsMapper;

    @Override
    public int insert(AwwMatchRecords record) {
        return awwMatchRecordsMapper.insert(record);
    }

    @Override
    public List<AwwMatchRecords> selectByPages(boolean predicateNextPage, String sellAddress, String buyAddress, long pageSize, long pageNum, int sort) {
        Map<String, Object> paramMap = new HashMap<>();
        if (StringUtils.isNotBlank(sellAddress)) {
            paramMap.put("sellAddress", sellAddress);
        }
        if (StringUtils.isNotBlank(buyAddress)) {
            paramMap.put("buyAddress", buyAddress);
        }
        paramMap.put("pageFrom", predicateNextPage ? (pageNum - 1) * (pageSize - 1) : (pageNum - 1) * pageSize);
        paramMap.put("pageSize", pageSize);

        if (sort == 0) {
            paramMap.put("sort", "create_time desc");
        } else if (sort == 1) {
            paramMap.put("sort", "create_time desc");
        } else if (sort == 2) {
            paramMap.put("sort", "create_time asc");
        }

        return awwMatchRecordsMapper.selectByPages(paramMap);
    }


}
