package com.management.user.usermanagement.publisher;

import com.management.user.usermanagement.config.MessagingConfig;
import com.management.user.usermanagement.dto.User;
import com.management.user.usermanagement.dto.UserStatus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserPublisher {

    @Autowired
    private RabbitTemplate template;

    @PostMapping("/users")
    public String postUser(@RequestBody User user) {
        //  user.setId(UUID.randomUUID().toString());
        System.out.println("user info : " + user.toString());
        UserStatus status = new UserStatus(user, "PROCESS", "User submitted");
        template.convertAndSend(MessagingConfig.TOPIC_EXCHANGE, MessagingConfig.ROUTING_KEY, user);
        return "Success";
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
