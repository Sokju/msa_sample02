package com.msa_sample02.zuul.server.filter;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String jsonData = null;
		
		try
		{
			
			InputStream stream = ctx.getResponseDataStream();
			jsonData = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
	        
	        if (jsonData == null) {
	        	log.info(String.format("[R][%-6s]%s", request.getMethod(), request.getRequestURL().toString()));
	            return null;
	        }
	        
	        ObjectNode node = new ObjectMapper().readValue(jsonData, ObjectNode.class);
	        
	        if (node.has("name")) {
	        	log.info(String.format("[R][%-6s]%s|%s", request.getMethod(), request.getRequestURL().toString(), node.get("name")));
	        } 
	        else
	        {
	        	log.info(String.format("[R][%-6s]%s|%s", request.getMethod(), request.getRequestURL().toString(), jsonData));
	        }
	        ctx.setResponseBody(jsonData);
	        
			//HttpServletResponse servletResponse = ctx.getResponse();
			//servletResponse.addHeader("X-Sample", UUID.randomUUID().toString());
	        
	        if (stream != null) stream.close();
	        
			
		}catch (IOException e) {
            rethrowRuntimeException(e);
        }
		
		return null;
	}
}