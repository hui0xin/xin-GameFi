package com.bixin.gameFi.aww.service;

import com.bixin.gameFi.aww.bean.DO.AwwArmInfo;
import com.bixin.gameFi.aww.bean.DO.AwwMarket;

import java.util.List;

/**
 * @author zhangcheng
 * create  2021/12/13
 */
public interface IAwwArmInfoService {

    List<AwwArmInfo> selectAll();

    List<AwwArmInfo> selectAll(List<Long> armIds);

    List<AwwArmInfo> selectAllByIds(List<Long> ids);

}
