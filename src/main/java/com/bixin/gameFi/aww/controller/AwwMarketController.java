package com.bixin.gameFi.aww.controller;

import com.bixin.gameFi.aww.bean.DO.AwwMarket;
import com.bixin.gameFi.aww.common.contants.PathConstant;
import com.bixin.gameFi.common.response.P;
import com.bixin.gameFi.aww.service.IAwwMarketService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangcheng
 * create  2021/12/9
 */
@RestController
@RequestMapping(PathConstant.AWW_REQUEST_PATH_PREFIX + "/market")
public class AwwMarketController {

    @Resource
    IAwwMarketService awwMarketService;

    @GetMapping("/getALL")
    public P getALL(@RequestParam(value = "startPrice", defaultValue = "0") long startPrice,
                    @RequestParam(value = "endPrice", defaultValue = "0") long endPrice,
                    @RequestParam(value = "raritys", defaultValue = "") List<Integer> raritys,
                    @RequestParam(value = "pageSize", defaultValue = "20") long pageSize,
                    @RequestParam(value = "pageNum", defaultValue = "1") long pageNum,
                    @RequestParam(value = "sort", defaultValue = "0") int sort) {

        if (pageNum <= 0 || pageSize <= 0 || sort < 0
                || startPrice < 0 || endPrice < 0 || endPrice < startPrice) {
            return P.failed("parameter is invalid");
        }

        List<AwwMarket> awwMarkets = awwMarketService.selectByPages(true,
                null,
                startPrice,
                endPrice,
                raritys,
                pageSize + 1,
                pageNum,
                sort);

        if (CollectionUtils.isEmpty(awwMarkets)) {
            return P.success(null, false);
        }

        boolean hasNext = false;
        if (awwMarkets.size() > pageSize) {
            awwMarkets = awwMarkets.subList(0, awwMarkets.size() - 1);
            hasNext = true;
        }

        return P.success(awwMarkets, hasNext);
    }

}
