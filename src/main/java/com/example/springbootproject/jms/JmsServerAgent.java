package com.example.springbootproject.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import static com.example.springbootproject.config.JmsConfig.LOGISTICS_TO_SERVER;
import static com.example.springbootproject.config.JmsConfig.SERVER_TO_LOGISTICS;

@Component
@Slf4j
public class JmsServerAgent {

    @Autowired
    JmsTemplate jmsTemplate;

    public void send(JmsOrder order){
        jmsTemplate.convertAndSend(SERVER_TO_LOGISTICS, order);
    }

    @JmsListener(destination = LOGISTICS_TO_SERVER)
    public void receiveMessageFromServer(String message){
        log.info("received <" + message + ">");
    }
}
