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
	
	/*
	 * 	-	execution.isolation.thread.timeoutInMilliseconds
			Hystrix 가 적용된 메서드의 타임아웃 지정
			이 타임아웃 내에 메서드가 완료되지 못하면 서킷브레이커가 닫혀있다고 하더라도 fallback 메서드가 호출
			보통 외부 API 를 호출하게되면 RestTemplate 과 같은 http client에도 connect, read timeout 등을 지정하게 하는데 
			hystrix timeout은 이를 포함하고 여유를 좀 더 두어 설정 기본값은 1초(1000)
		
		- 	metrics.rollingStats.timeInMilliseconds
			서킷 브레이커가 열리기 위한 조건 체크 시간
			아래에서 살펴볼 몇가지 조건들과 함께 조건을 정의하게 되는데 "10초간 50% 실패하면 서킷 브레이커 발동" 이라는 조건이 
			정의되어 있다면 여기서 10초를 맡는다. 기본값은 10초(10000)

		- 	circuitBreaker.errorThresholdPercentage
			서킷 브레이커가 발동할 에러 퍼센트 지정 기본값은 50

		- 	circuitBreaker.requestVolumeThreshold
			서킷 브레이커가 열리기 위한 최소 요청 조건
			즉 이 값이 20으로 설정되어 있다면 10초간 19개의 요청이 들어와서 19개가 전부 실패하더라도 서킷 브레이커는 열리지 않음. 기본값은 20

		- 	circuitBreaker.sleepWindowInMilliseconds
			서킷 브레이커가 열렸을때 얼마나 지속될지 설정. 기본값은 5초(5000)

		- 	coreSize
			Hystrix 작동방식은 Thread를 이용하는 Thread 방식과 Semaphore 방식이 있다. 
			Thread 를 이용할 경우 core size를 지정하는 속성이다. 
			넷플릭스에서는 공식 가이드에 왠만하면 Thread 방식을 권장.(디폴트 설정도 Thread 방식이다.) 기본 coreSize 는 10
	 */
	
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
