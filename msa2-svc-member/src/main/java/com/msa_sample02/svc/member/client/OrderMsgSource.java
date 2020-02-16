package com.msa_sample02.svc.member.client;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderMsgSource {

	@Output("orderMsgChannel")
	MessageChannel orderMsg();

}