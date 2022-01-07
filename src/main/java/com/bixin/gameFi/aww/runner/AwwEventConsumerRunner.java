package com.bixin.gameFi.aww.runner;

import com.bixin.gameFi.aww.bean.dto.TakeOrderEventDto;
import com.bixin.gameFi.aww.common.enums.AwwEventType;
import com.bixin.gameFi.common.function.CaseFun;
import com.bixin.gameFi.aww.common.queue.AwwEventBlockingQueue;
import com.bixin.gameFi.common.factory.NamedThreadFactory;
import com.bixin.gameFi.core.provider.GameFiDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangcheng
 * create  2021-12-09 6:37 下午
 */
@Slf4j
@Component
public class AwwEventConsumerRunner implements ApplicationRunner {

    @Resource
    GameFiDispatcher gameFiDispatcher;

    ThreadPoolExecutor poolExecutor;
    ObjectMapper mapper = new ObjectMapper();
    static int parkMilliSeconds = 2000;

    @PostConstruct
    public void init() {
        poolExecutor = new ThreadPoolExecutor(1, 1, 0,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(),
                new NamedThreadFactory("AwwEventConsumer-", true));
    }

    @PreDestroy
    public void destroy() {
        try {
            if (Objects.isNull(poolExecutor)) {
                return;
            }
            poolExecutor.shutdown();
            poolExecutor.awaitTermination(1, TimeUnit.SECONDS);
            log.info("AwwEventConsumerRunner ThreadPoolExecutor stopped");
        } catch (InterruptedException ex) {
            log.error("AwwEventConsumerRunner InterruptedException: ", ex);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        poolExecutor.execute(() -> process(args));
    }

    public void process(ApplicationArguments args) {
        Map<AwwEventType, LinkedBlockingQueue<JsonNode>> queueMap = AwwEventBlockingQueue.queueMap;
        for (; ; ) {
            queueMap.entrySet().forEach(entry -> {
                AwwEventType type = entry.getKey();
                LinkedBlockingQueue<JsonNode> jsonNodes = entry.getValue();
                JsonNode node = jsonNodes.poll();
                if (Objects.nonNull(node)) {
                    awwDispatcher(type, node);
                }
            });
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(parkMilliSeconds));
        }
    }

    private void awwDispatcher(AwwEventType type, JsonNode node) {
        CaseFun.builder().hasContinue(true).build()
                .elseCase(type, value -> AwwEventType.TAKE_ORDER_EVENT == value, value -> {
                    TakeOrderEventDto takeOrderEventDto = mapper.convertValue(node, TakeOrderEventDto.class);
                    // TODO: 2022/1/6 debug
                    log.info("AwwEventConsumerRunner awwDispatcher info {}", takeOrderEventDto);
                    gameFiDispatcher.dispatch(TakeOrderEventDto.of(takeOrderEventDto));
                });
//                .elseCase(type, value -> AwwEventType.LIQUIDITY_EVENT == value, value -> {
//                    LiquidityEventDto liquidityEventDto = mapper.convertValue(node, LiquidityEventDto.class);
//                    swapDispatcher.dispatch(LiquidityEventDto.of(liquidityEventDto));
//                });
    }

}
