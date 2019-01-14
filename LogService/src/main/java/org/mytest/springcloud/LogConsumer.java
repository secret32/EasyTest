package org.mytest.springcloud;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author zhouwei
 * @since 1/14/2019
 */
@Component
@RabbitListener(queues = {"lan"}, containerFactory = "rabbitListenerContainerFactory")
public class LogConsumer {

    @RabbitHandler
    public void recv(String message) {
        System.out.println(message);
    }

}
