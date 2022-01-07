package com.bixin.gameFi.aww.service.Impl;

import com.bixin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.bixin.gameFi.aww.core.mapper.AwwMatchRecordsMapper;
import com.bixin.gameFi.aww.service.IAwwMatchRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author zhangcheng
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
