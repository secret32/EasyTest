package org.mytest.springcloud;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_WORKER = "queue_worker";
    public static final String QUEUE_LOG_INFO = "queue_log_info";
    public static final String QUEUE_LOG_ERROR = "queue_log_error";
    public static final String QUEUE_ALL = "queue_all";

    @Bean
    public Queue workQueue() {
        return new Queue(QUEUE_WORKER);
    }

    @Bean
    public Queue logInfoQueue() {
        return new Queue(QUEUE_LOG_INFO);
    }

    @Bean
    public Queue logErrorQueue() {
        return new Queue(QUEUE_LOG_ERROR);
    }

    @Bean
    public Queue allQueue() {
        return new Queue(QUEUE_ALL);
    }

}
