package org.mytest.springcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@RabbitListener(queues = {RabbitConfig.QUEUE_WORKER})
@Slf4j
@Component
public class WorkerReceiver {

    @RabbitHandler
    public void recv(String message) {
        log.info(message);
    }

}
