package com.bixin.gameFi.aww.runner;

import com.bixin.gameFi.aww.common.enums.AwwEventType;
import com.bixin.gameFi.aww.common.queue.AwwEventBlockingQueue;
import com.bixin.gameFi.aww.config.AwwConfig;
import com.bixin.gameFi.common.factory.NamedThreadFactory;
import com.bixin.gameFi.core.redis.RedisCache;
import com.bixin.gameFi.common.utils.LocalDateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.stereotype.Component;
import org.starcoin.api.StarcoinSubscriber;
import org.starcoin.bean.EventFilter;
import org.starcoin.bean.EventNotification;
import org.starcoin.bean.EventNotificationResult;
import org.web3j.protocol.websocket.WebSocketService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zhangcheng
 * create          2021-08-12 11:54 上午
 */
@Slf4j
@Component
public class AwwEventSubscriberRunner implements ApplicationRunner {

    @Resource
    AwwConfig gameConfig;
    @Resource
    RedisCache redisCache;

    AtomicLong atomicSum = new AtomicLong(0);
    static final long initTime = 2000L;
    static final long initIntervalTime = 5000L;
    static final long maxIntervalTime = 60 * 1000L;
    //滤重过期时间 默认20分钟
    static final long duplicateExpiredTime = 20 * 60;

    static final String separator = "::";
    static final String underline = "_";
    ObjectMapper mapper = new ObjectMapper();

    ThreadPoolExecutor poolExecutor;

    @PostConstruct
    public void init() {
        poolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), new NamedThreadFactory("SwapEventSubscriber-", true));
    }

    @PreDestroy
    public void destroy() {
        try {
            if (Objects.isNull(poolExecutor)) {
                return;
            }
            poolExecutor.shutdown();
            poolExecutor.awaitTermination(1, TimeUnit.SECONDS);
            log.info("GameEventSubscriberRunner poolExecutor stopped");
        } catch (InterruptedException ex) {
            log.error("GameEventSubscriberRunner InterruptedException: ", ex);
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        poolExecutor.execute(() -> process(args));
    }

    public void process(ApplicationArguments args) {
        String[] sourceArgs = 0 == args.getSourceArgs().length ? new String[]{""} : args.getSourceArgs();
        log.info("GameEventSubscriberRunner start running [{}]", sourceArgs);
        try {
            WebSocketService service = new WebSocketService("ws://" + gameConfig.getWebsocket().getWebsocketHost() + ":" + gameConfig.getWebsocket().getWebsocketPort(), true);
            service.connect();
            StarcoinSubscriber subscriber = new StarcoinSubscriber(service);
            EventFilter eventFilter = new EventFilter(Collections.singletonList(gameConfig.getCommon().getContractAddress()));
            Flowable<EventNotification> notificationFlowable = subscriber.newTxnSendRecvEventNotifications(eventFilter);

            Map<AwwEventType, LinkedBlockingQueue<JsonNode>> queueMap = AwwEventBlockingQueue.queueMap;

            notificationFlowable.blockingIterable().forEach(b -> {
                EventNotificationResult eventResult = b.getParams().getResult();
                AwwEventType eventType = AwwEventType.of(getEventName(eventResult.getTypeTag()));
                JsonNode data = eventResult.getData();

                // FIXME: 2021/8/30 debug
                try {
                    log.info("GameEventSubscriberRunner infos: {}", mapper.writeValueAsString(eventResult));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                if (Objects.isNull(eventType) || Objects.isNull(data)) {
                    return;
                }
                if (duplicateEvent(eventResult)) {
                    log.info("GameEventSubscriberRunner duplicate event data {}", eventResult);
                    return;
                }

                queueMap.get(eventType).offer(data);
            });

        } catch (Exception e) {
            long duration = initTime + (atomicSum.incrementAndGet() - 1) * initIntervalTime;
            duration = Math.min(duration, maxIntervalTime);
            log.error("GameEventSubscriberRunner run exception count {}, next retry {}, params {}",
                    atomicSum.get(), duration, gameConfig.getWebsocket(), e);
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(duration));
            DefaultApplicationArguments applicationArguments = new DefaultApplicationArguments("retry " + atomicSum.get());
            this.process(applicationArguments);
        }
    }

    private String getEventName(String typeTag) {
        return typeTag.split(separator)[2];
    }

    /**
     * false 不存在
     * true 已存在
     *
     * @param eventResult
     * @return
     */
    public boolean duplicateEvent(EventNotificationResult eventResult) {
        String typeTag = eventResult.getTypeTag();
        String seqNumber = eventResult.getEventSeqNumber();

        String key = null;
        try {
            key = URLEncoder.encode(typeTag, "utf8") + seqNumber;
        } catch (UnsupportedEncodingException e) {
            log.error("GameEventSubscriberRunner exception ", e);
        }
        log.info("GameEventSubscriberRunner duplicate event redis key {}", key);

        if (Objects.isNull(key)) {
            return true;
        }

        Long now = LocalDateTimeUtil.getMilliByTime(LocalDateTime.now());
        return !redisCache.tryGetDistributedLock(
                key,
                UUID.randomUUID().toString().replaceAll("-", "") + now,
                duplicateExpiredTime
        );
    }

}
