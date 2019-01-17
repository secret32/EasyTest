package org.mytest.springcloud.work;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_WORKER = "exchange_worker";
    public static final String QUEUE_WORKER = "queue_worker";

    public static final String EXCHANGE_LOG = "exchange_log";
    public static final String QUEUE_LOG_INFO = "queue_log_info";
    public static final String QUEUE_LOG_ERROR = "queue_log_error";

    public static final String EXCHANGE_BROADCAST = "exchange_broadcast";

    public static final String QUEUE_ALL = "queue_all";

    public static final String ROUTE_KEY_WORKER = "work";
    public static final String ROUTE_KEY_LOG = "log.#";
    public static final String ROUTE_KEY_INFO = "log.info";
    public static final String ROUTE_KEY_ERROR = "log.error";
    public static final String ROUTE_KEY_OTHER = "log.other";

    @Bean("workerExchange")
    public Exchange workerExchange() {
        return new DirectExchange(EXCHANGE_WORKER);
    }

    @Bean("logExchange")
    public Exchange logExchange() {
        return new TopicExchange(EXCHANGE_LOG);
    }

    @Bean("broadcastExchange")
    public Exchange broadcastExchange() {
        return new FanoutExchange(EXCHANGE_BROADCAST);
    }

    @Bean("workerQueue")
    public Queue workerQueue() {
        return new Queue(QUEUE_WORKER);
    }

    @Bean("logInfoQueue")
    public Queue logInfoQueue() {
        return new Queue(QUEUE_LOG_INFO);
    }

    @Bean("logErrorQueue")
    public Queue logErrorQueue() {
        return new Queue(QUEUE_LOG_ERROR);
    }

    @Bean("allQueue")
    public Queue allQueue() {
        return new Queue(QUEUE_ALL);
    }

    @Bean
    public Binding workBinding(Exchange workerExchange, Queue workerQueue) {
        return BindingBuilder.bind(workerQueue).to(workerExchange).with(ROUTE_KEY_WORKER).noargs();
    }

    @Bean
    public Binding logInfoBinding(Exchange logExchange, Queue logInfoQueue) {
        return BindingBuilder.bind(logInfoQueue).to(logExchange).with(ROUTE_KEY_INFO).noargs();
    }

    @Bean
    public Binding logErrorBinding(Exchange logExchange, Queue logErrorQueue) {
        return BindingBuilder.bind(logErrorQueue).to(logExchange).with(ROUTE_KEY_ERROR).noargs();
    }

    @Bean
    public Binding allBinding1(Exchange workerExchange, Queue allQueue) {
        return BindingBuilder.bind(allQueue).to(workerExchange).with(ROUTE_KEY_WORKER).noargs();
    }

    @Bean
    public Binding allBinding2(Exchange logExchange, Queue allQueue) {
        return BindingBuilder.bind(allQueue).to(logExchange).with(ROUTE_KEY_LOG).noargs();
    }

    @Bean
    public Binding broadcastBinding1(FanoutExchange exchange, Queue allQueue) {
        return BindingBuilder.bind(allQueue).to(exchange);
    }

    @Bean
    public Binding broadcastBinding2(FanoutExchange exchange, Queue workerQueue) {
        return BindingBuilder.bind(workerQueue).to(exchange);
    }

}
