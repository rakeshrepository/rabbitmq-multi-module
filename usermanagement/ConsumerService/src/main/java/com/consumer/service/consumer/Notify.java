package com.consumer.service.consumer;

import com.consumer.service.dto.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class Notify {

    private static final Logger log = LoggerFactory.getLogger(Notify.class);

    @RabbitListener(queues = "javatechie_queue")
    public void consumeUser(UserStatus userStatus){
        log.info("User details : {}",userStatus);
    }
}
