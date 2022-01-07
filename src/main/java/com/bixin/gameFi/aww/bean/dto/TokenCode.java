package com.bixin.gameFi.aww.bean.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangcheng
 * create  2021/12/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenCode {

    private String addr;
    private String module_name;
    private String name;

}
