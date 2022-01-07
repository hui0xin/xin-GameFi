-- 创建数据库
CREATE DATABASE IF NOT EXISTS ido_server DEFAULT CHARACTER SET utf8mb4;


DROP TABLE IF EXISTS aww_market;
CREATE TABLE aww_market
(
    id             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    chain_id       bigint(20) NOT NULL COMMENT '链上 id',
    aww_id         bigint(20) NOT NULL COMMENT 'aww_info表的id',
    aww_name       varchar(128)   DEFAULT NULL COMMENT ' nft名称',
    owner          varchar(128)   DEFAULT NULL COMMENT '当前持有者',
    address        varchar(128)   DEFAULT NULL COMMENT '合约地址',
    rarity         int(2)         DEFAULT NULL COMMENT '稀有度',
    sell_price     DECIMAL(38, 0) DEFAULT 0 COMMENT '售价',
    stamina        int(2)     NOT NULL COMMENT '体力值',
    used_stamina   int(2)     NOT NULL COMMENT '已经使用体力值',
    win_rate_bonus int(6) NOT NULL COMMENT '附加胜率',
    icon           varchar(256)   DEFAULT NULL COMMENT '图片地址',
    create_time    bigint(20) NOT NULL COMMENT '创建时间',
    update_time    bigint(20) NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `gid_ry_sp` (`sell_price`, `rarity`),
    KEY `ow_address` (`owner`, `address`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4 COMMENT ='aww市场销售列表';


DROP TABLE IF EXISTS aww_match_records;
CREATE TABLE `aww_match_records`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `sell_address` varchar(64)  NOT NULL COMMENT '卖家地址',
    `buy_address`  varchar(64)  NOT NULL COMMENT '买家地址',
    `aww_id`       bigint(20)   NOT NULL COMMENT 'aww_info表的id',
    `icon`         varchar(256) NOT NULL COMMENT '图片链接',
    `aww_name`     varchar(32)  NOT NULL COMMENT 'nft 名称',
    `price`        decimal(38, 0) DEFAULT '0' COMMENT '成交价',
    `fee`          decimal(38, 0) DEFAULT '0' COMMENT '手续费',
    `match_time`   bigint(20)   NOT NULL COMMENT '成交时间',
    `create_time`  bigint(20)   NOT NULL COMMENT '创建时间',
    `update_time`  bigint(20)   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4 COMMENT ='nft事件表';


DROP TABLE IF EXISTS aww_arm_info;
CREATE TABLE `aww_arm_info`
(
    `id`             bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `arm_id`         bigint(20) NOT NULL COMMENT 'nft id',
    `name`           varchar(128)   NOT NULL COMMENT '名称',
    `rarity`         tinyint(4) NOT NULL COMMENT '稀有度',
    `stamina`        tinyint(4) NOT NULL COMMENT '体力值',
    `win_rate_bonus` tinyint(4) NOT NULL COMMENT '附加胜率',
    `image_link`     varchar(256) NOT NULL COMMENT '图片链接',
    `image_data`     mediumtext COMMENT '图片数据',
    `created`        tinyint(1)   NOT NULL COMMENT '已创建, 0未上链, 1已上链',
    `create_time`    bigint(20)   NOT NULL COMMENT '创建时间',
    `update_time`    bigint(20)   NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_nft_id` (`arm_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10000
  DEFAULT CHARSET = utf8mb4 COMMENT ='手臂信息记录表';
