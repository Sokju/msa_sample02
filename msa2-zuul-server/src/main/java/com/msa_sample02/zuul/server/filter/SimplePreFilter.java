package com.msa_sample02.zuul.server.filter;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_ENTITY_KEY;
import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimplePreFilter extends ZuulFilter {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
    public Object run() {
        RequestContext 		ctx 	=	RequestContext.getCurrentContext();
        HttpServletRequest 	request = 	ctx.getRequest();
        String jsonData	=	null;
        String logType 	= 	"Q";	//	Request 로그
        String reqMethod= 	null;
        String reqURL 	= 	null;
        String keyWord 	= 	"name"; // 전문상 GUID 등이 필드 검색 Key
        		
        try {
        	
        	/*
            InputStream in = (InputStream) ctx.get(REQUEST_ENTITY_KEY);
            if (in == null) {
                in = request.getInputStream();
            }
            json	=	StreamUtils.copyToString(in, Charset.forName("UTF-8"));
            log.info("TYPE1 : "+json);

            if (in != null) in.close();

            */
        	
        	reqMethod 	= 	request.getMethod();
        	reqURL   	=	request.getRequestURL().toString();
        	
            if (request.getContentLength() > 0 ) {
            	jsonData = CharStreams.toString(request.getReader());
            }
            
            if (jsonData == null) {
            	log.debug(String.format("[%s][%-6s]%s|Message is null", logType, reqMethod, reqURL));
                return null;
            }
            
            if (jsonData.length() == 0) {
            	log.debug(String.format("[%s][%-6s]%s|Message length is 0", logType, reqMethod, reqURL));
                return null;
            }
            
            //ObjectNode jnode = new ObjectMapper().readValue(jsonData, ObjectNode.class);
	        JsonNode tnode = new ObjectMapper().readTree(jsonData);
	        JsonNode jnode = null;
	        
	        if (tnode != null && (jnode = tnode.path("grid1")).has(keyWord)) {
	        	log.debug(String.format("[%s][%-6s]%s|%s", logType, reqMethod, reqURL, jnode.get(keyWord)));
	        } 
	        else
	        {
	        	log.debug(String.format("[%s][%-6s]%s|%s", logType, reqMethod, reqURL, jsonData));
	        }

        }catch (IOException e) {
            rethrowRuntimeException(e);
        }
        
//        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (!validateToken(authorizationHeader)) {
//            ctx.setSendZuulResponse(false);
//            ctx.setResponseBody("API key not authorized");
//            ctx.getResponse().setHeader("Content-Type", "text/plain;charset=UTF-8");
//            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
//        }
        return null;
    }
    
    private boolean validateToken(String tokenHeader) {
        // do something to validate the token
        return true;
    }
}