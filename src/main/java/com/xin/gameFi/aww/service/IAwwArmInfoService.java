package com.xin.gameFi.aww.service;

import com.xin.gameFi.aww.bean.DO.AwwArmInfo;

import java.util.List;

/**
 * create  2021/12/13
 */
public interface IAwwArmInfoService {

    List<AwwArmInfo> selectAll();

    List<AwwArmInfo> selectAll(List<Long> armIds);

    List<AwwArmInfo> selectAllByIds(List<Long> ids);

}
