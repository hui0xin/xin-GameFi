package com.xin.gameFi.aww.service.Impl;

import com.xin.gameFi.aww.bean.DO.AwwArmInfo;
import com.xin.gameFi.aww.core.mapper.AwwArmInfoMapper;
import com.xin.gameFi.aww.core.wrapDDL.AwwArmInfoDDL;
import com.xin.gameFi.aww.service.IAwwArmInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * create  2021/12/13
 */
@Service
public class AwwArmInfoImpl implements IAwwArmInfoService {

    @Resource
    AwwArmInfoMapper awwArmInfoMapper;

    @Override
    public List<AwwArmInfo> selectAll() {
        return awwArmInfoMapper.selectByDDL(new AwwArmInfoDDL());
    }

    @Override
    public List<AwwArmInfo> selectAll(List<Long> armIds) {
        AwwArmInfoDDL ddl = new AwwArmInfoDDL();
        AwwArmInfoDDL.Criteria criteria = ddl.createCriteria();
        criteria.andArmIdIn(armIds);
        return awwArmInfoMapper.selectByDDL(ddl);
    }

    @Override
    public List<AwwArmInfo> selectAllByIds(List<Long> ids) {
        AwwArmInfoDDL ddl = new AwwArmInfoDDL();
        AwwArmInfoDDL.Criteria criteria = ddl.createCriteria();
        criteria.andIdIn(ids);
        return awwArmInfoMapper.selectByDDL(ddl);
    }

}
