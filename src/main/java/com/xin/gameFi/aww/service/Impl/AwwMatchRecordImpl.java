package com.xin.gameFi.aww.service.Impl;

import com.xin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.xin.gameFi.aww.core.mapper.AwwMatchRecordsMapper;
import com.xin.gameFi.aww.service.IAwwMatchRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * create  2022/1/5
 */
@Service
public class AwwMatchRecordImpl implements IAwwMatchRecordService {

    @Resource
    AwwMatchRecordsMapper matchRecordsMapper;

    @Override
    public int insert(AwwMatchRecords record) {
        return matchRecordsMapper.insert(record);
    }
}
