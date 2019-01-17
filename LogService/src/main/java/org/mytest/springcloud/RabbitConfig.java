package org.mytest.springcloud;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
public class RabbitConfig {
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    public static final String QUEUE_WORKER = "queue_worker";
    public static final String QUEUE_LOG_INFO = "queue_log_info";
    public static final String QUEUE_LOG_ERROR = "queue_log_error";
    public static final String QUEUE_ALL = "queue_all";

    @Bean("cachingConnectionFactory")
    public ConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cf = new CachingConnectionFactory();
        cf.setHost(host);
        cf.setPort(port);
        cf.setUsername(username);
        cf.setPassword(password);
        cf.setVirtualHost(virtualHost);
        return cf;
    }

    @Bean("containerFactory")
    public SimpleRabbitListenerContainerFactory containerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        ThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("mq-consumer-%d").build();
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(4, 10, 15,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), threadFactory);
        factory.setTaskExecutor(tpe);
        configurer.configure(factory, connectionFactory);
        return factory;
    }

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
