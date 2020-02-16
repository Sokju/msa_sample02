package com.msa_sample02.svc.member.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OrderSender {

    @Autowired
    OrderMsgSource orderMsgSource;

    public void send(String message) {
    	orderMsgSource.orderMsg().send(message(message));
    }
    
    public void send(Message msg) {
    	orderMsgSource.orderMsg().send(msg);
    }

    private static final <T> Message<T> message(T val) {
        return MessageBuilder.withPayload(val).build();
    }
}