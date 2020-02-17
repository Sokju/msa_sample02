package com.msa_sample02.svc.order.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(OrderMsgBinding.class)
public class OrderMsgListener {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@StreamListener(target = OrderMsgBinding.ORD_CHANNEL)
    public void processOrderMsg(String msg) {
        log.debug("SUBSCRIBE_MSG : " + msg);
    }
}
