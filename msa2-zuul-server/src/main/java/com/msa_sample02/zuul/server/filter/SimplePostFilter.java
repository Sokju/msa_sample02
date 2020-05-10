package com.msa_sample02.zuul.server.filter;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimplePostFilter extends ZuulFilter {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String jsonData	=	null;
        String logType 	= 	"R";	//	Request 로그
        String reqMethod= 	null;
        String reqURL 	= 	null;
        String keyWord 	= 	"name"; // 전문상 GUID 등이 필드 검색 Key
		
		try
		{
			
			reqMethod 	= 	request.getMethod();
        	reqURL   	=	request.getRequestURL().toString();
        	
			InputStream stream = ctx.getResponseDataStream();
			jsonData = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	        
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
			//HttpServletResponse servletResponse = ctx.getResponse();
			//servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
	        
	        if (stream != null) stream.close();
	        
			
		}
		catch (IOException e) 
		{
            rethrowRuntimeException(e);
        } 
		finally 
		{
			ctx.setResponseBody(jsonData);
        }
		
		return null;
	}
}