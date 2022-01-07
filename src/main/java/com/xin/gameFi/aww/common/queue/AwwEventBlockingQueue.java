package com.xin.gameFi.aww.common.queue;

import com.xin.gameFi.common.contants.CommonConstant;
import com.xin.gameFi.aww.common.enums.AwwEventType;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * create          2021-08-12 11:54 上午
 */
@Slf4j
@Component
public class AwwEventBlockingQueue {

    //订阅swap事件 队列
    public static Map<AwwEventType, LinkedBlockingQueue<JsonNode>> queueMap = new HashMap<>() {{
        put(AwwEventType.PLACE_ORDER_EVENT, new LinkedBlockingQueue<>(CommonConstant.GAME_EVENT_QUEUE_SIZE));
        put(AwwEventType.TAKE_ORDER_EVENT, new LinkedBlockingQueue<>(CommonConstant.GAME_EVENT_QUEUE_SIZE));
        put(AwwEventType.CANCEL_ORDER_EVENT, new LinkedBlockingQueue<>(CommonConstant.GAME_EVENT_QUEUE_SIZE));
    }};

}
