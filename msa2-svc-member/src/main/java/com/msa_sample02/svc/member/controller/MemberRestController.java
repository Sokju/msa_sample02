package com.msa_sample02.svc.member.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.msa_sample02.svc.member.client.OrderServiceClient;
import com.msa_sample02.svc.member.domain.Member;
import com.msa_sample02.svc.member.service.MemberService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RefreshScope
@RestController
public class MemberRestController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
    private DiscoveryClient discoveryClient;
	
	@Autowired
	private MemberService	memberService;
	
	@HystrixCommand(fallbackMethod = "fallbackMemberByName", commandProperties = {
			   @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
			   @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
			   @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10"),
			   @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
			   @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
			}, threadPoolProperties = @HystrixProperty(name = "coreSize", value = "100"))
	@RequestMapping(value = "/v2/member/{memberName}", method = RequestMethod.GET)
	public Member getMemberByName(@PathVariable("memberName") String memberName) {
		
		log.debug("############ getMemberByName : " + memberName);
		
		return memberService.findByName(memberName);
	}
	
	public Member fallbackMemberByName(@PathVariable("memberName") String memberName) {
		
		log.debug("############ fallbackMemberByName : " + memberName);
		
		Member member = new Member();
		member.setName(memberName);
		member.setComment("fallbackMemberByName");
		return member;
	}


	@RequestMapping(value = "/v2/member", method = RequestMethod.POST)
	public void createMember(@Valid @RequestBody Member member) {
		
		log.debug("############ createMember : " + member.getName());
		
		memberService.createMember(member);
	}
	
	@HystrixCommand
	@RequestMapping(value = "/v2/member", method = RequestMethod.PUT)
	public void updateMember(@Valid @RequestBody Member member) {
		
		log.debug("############ updateMember : " + member.getName());
		
		memberService.updateMember(member);
	}
	
	@HystrixCommand
	@RequestMapping(value = "/v2/member/{memberName}", method = RequestMethod.DELETE)
	public void deleteMemberByName(@PathVariable("memberName") String memberName) {
		
		log.debug("############ updateMember : " + memberName);
		
		memberService.deleteMember(memberName);
	}
	
	@HystrixCommand(fallbackMethod = "fallbackFunction")
	@RequestMapping(value = "/v2/member/fallbackTest", method = RequestMethod.GET)
	public String fallbackTest() throws Throwable{
		throw new Throwable("fallbackTest");
	}
	public String fallbackFunction(){
		return "fallbackFunction()";
	}
	
	@GetMapping
    public String load() {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://msa2-svc-order:9092";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);

        String serviceList = "";
        if (discoveryClient != null) {
            List<String> services = this.discoveryClient.getServices();

            for (String service : services) {

                List<ServiceInstance> instances = this.discoveryClient.getInstances(service);

                serviceList += ("[" + service + " : " + ((!CollectionUtils.isEmpty(instances)) ? instances.size() : 0) + " instances ]");
            }
        }

        return String.format("\"Message from backend is: %s <br/> Services : %s", response.getBody(), serviceList);
    }
}
