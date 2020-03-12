package com.msa_sample02.svc.member.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RibbonClient(name = "msa2-svc-order")
public interface OrderServiceClient {

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	void order(String member);

}
