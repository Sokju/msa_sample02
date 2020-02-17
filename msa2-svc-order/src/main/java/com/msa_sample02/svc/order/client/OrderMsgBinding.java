package com.msa_sample02.svc.order.client;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderMsgBinding {

	String ORD_CHANNEL = "orderMsgChannel";
	
	@Input(ORD_CHANNEL)
	SubscribableChannel orderMsg();
}
