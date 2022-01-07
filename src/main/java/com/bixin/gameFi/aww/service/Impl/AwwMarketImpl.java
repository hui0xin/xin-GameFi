package com.bixin.gameFi.aww.service.Impl;

import com.bixin.gameFi.aww.bean.DO.AwwMarket;
import com.bixin.gameFi.aww.core.mapper.AwwMarketMapper;
import com.bixin.gameFi.aww.core.wrapDDL.AwwMarketDDL;
import com.bixin.gameFi.aww.service.IAwwMarketService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
@Service
public class AwwMarketImpl implements IAwwMarketService {

    @Resource
    AwwMarketMapper awwMarketMapper;

    @Override
    public int insert(AwwMarket record) {
        return awwMarketMapper.insert(record);
    }

    @Override
    public List<AwwMarket> selectAll() {
        return awwMarketMapper.selectByDDL(new AwwMarketDDL());
    }

    @Override
    public int deleteAll() {
        return awwMarketMapper.deleteByDDL(new AwwMarketDDL());
    }

    @Override
    public int deleteById(List<Long> ids) {
        AwwMarketDDL ddl = new AwwMarketDDL();
        AwwMarketDDL.Criteria criteria = ddl.createCriteria();
        criteria.andIdIn(ids);
        return awwMarketMapper.deleteByDDL(ddl);
    }

    @Override
    public int updateById(AwwMarket record) {
        return awwMarketMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<AwwMarket> selectByPages(boolean predicateNextPage,
                                         String owner,
                                         Long startPrice,
                                         Long endPrice,
                                         List<Integer> raritys,
                                         Long pageSize,
                                         Long pageNum,
                                         int sort) {
        Map<String, Object> paramMap = new HashMap<>();
        if (Objects.nonNull(startPrice) && startPrice > 0) {
            paramMap.put("startPrice", startPrice);
        }
        if (Objects.nonNull(endPrice) && endPrice > 0) {
            paramMap.put("endPrice", endPrice);
        }
        if(!CollectionUtils.isEmpty(raritys)){
            paramMap.put("raritys", raritys);
        }
        paramMap.put("pageFrom", predicateNextPage ? (pageNum - 1) * (pageSize - 1) : (pageNum - 1) * pageSize);
        paramMap.put("pageSize", pageSize);
        paramMap.put("owner", owner);

        if (sort == 0) {
            paramMap.put("sort", "create_time desc");
        } else if (sort == 1) {
            paramMap.put("sort", "sell_price desc, create_time desc");
        } else if (sort == 2) {
            paramMap.put("sort", "sell_price asc, create_time desc");
        }

        return awwMarketMapper.selectByPages(paramMap);
    }

}

