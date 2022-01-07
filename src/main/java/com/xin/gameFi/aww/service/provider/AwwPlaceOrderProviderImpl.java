package com.xin.gameFi.aww.service.provider;

import com.xin.gameFi.aww.bean.DO.AwwArmInfo;
import com.xin.gameFi.aww.bean.DO.AwwMatchRecords;
import com.xin.gameFi.aww.service.IAwwArmInfoService;
import com.xin.gameFi.aww.service.IAwwMatchRecordService;
import com.xin.gameFi.core.provider.IGameFiProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * create   2021/12/2
 */
@Slf4j
@Component
public class AwwPlaceOrderProviderImpl implements IGameFiProvider<AwwMatchRecords> {

    @Resource
    IAwwMatchRecordService awwMatchRecordService;
    @Resource
    IAwwArmInfoService awwArmInfoService;

    @Override
    public void dispatcher(AwwMatchRecords record) {
        // TODO: 2022/1/6 debug
        log.info("AwwPlaceOrderProvider dispatcher record info {}", record);
        List<AwwArmInfo> awwArmInfos = awwArmInfoService.selectAll(Arrays.asList(record.getAwwId()));
        if (CollectionUtils.isEmpty(awwArmInfos)) {
            log.error("AwwPlaceOrderProvider dispatcher awwArmInfos is empty {}", record.getAwwId());
        }
        AwwArmInfo awwArmInfo = awwArmInfos.get(0);
        record.setAwwId(awwArmInfo.getId());
        record.setIcon(awwArmInfo.getImageLink());
        record.setAwwName(awwArmInfo.getName());

        awwMatchRecordService.insert(record);
    }

}
