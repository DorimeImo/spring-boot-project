package com.example.springbootproject.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.example.springbootproject.config.JmsConfig.SERVER_TO_LOGISTICS;
import static com.example.springbootproject.config.JmsConfig.LOGISTICS_TO_SERVER;

import javax.jms.Session;

@Slf4j
@Component
public class JmsLogisticsAgent {

    @Autowired
    JmsTemplate jmsTemplate;

    @JmsListener(destination = SERVER_TO_LOGISTICS)
    public void receiveMessageFromServer(@Payload JmsOrder order,
                                         @Headers MessageHeaders headers,
                                         Message message, Session session){
        log.info("received <" + order + ">");

        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("######          Message Details           #####");
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("headers: " + headers);
        log.info("message: " + message);
        log.info("session: " + session);
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");

        jmsTemplate.convertAndSend(LOGISTICS_TO_SERVER, "30 min");
    }
}
