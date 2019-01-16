package org.mytest.springcloud.work;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

import static org.mytest.springcloud.work.RabbitConfig.*;

@Component
@Slf4j
public class WorkJob {

    private static final String[] LOG_LEVEL = {"debug", "info", "warn", "error", "fatal"};

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    @Autowired
    private AmqpTemplate rabbitmqTemplate;

    @Scheduled(fixedRate = 5000)
    public void work() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "Wall");
        jsonObj.put("age", 18);
        jsonObj.put("unixtime", System.currentTimeMillis());
        rabbitmqTemplate.convertAndSend(EXCHANGE_WORKER, ROUTE_KEY_WORKER, jsonObj.toJSONString());
    }

    @Scheduled(fixedRate = 10000)
    public void log() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "Lucy");
        jsonObj.put("age", 17);
        jsonObj.put("unixtime", System.currentTimeMillis());
        String level = LOG_LEVEL[RANDOM.nextInt(LOG_LEVEL.length)];
        jsonObj.put("level", level);
        switch (level) {
            case "info":
                rabbitmqTemplate.convertAndSend(EXCHANGE_LOG, ROUTE_KEY_INFO, jsonObj.toJSONString());
                break;
            case "error":
                rabbitmqTemplate.convertAndSend(EXCHANGE_LOG, ROUTE_KEY_ERROR, jsonObj.toJSONString());
                break;
            default:
                rabbitmqTemplate.convertAndSend(EXCHANGE_LOG, ROUTE_KEY_OTHER, jsonObj.toJSONString());
                break;
        }
    }

    @Scheduled(fixedRate = 8000)
    public void broadcast() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", "Bruce");
        jsonObj.put("age", 33);
        jsonObj.put("unixtime", System.currentTimeMillis());
        rabbitmqTemplate.convertAndSend(EXCHANGE_BROADCAST, "", jsonObj.toJSONString());
    }

}
