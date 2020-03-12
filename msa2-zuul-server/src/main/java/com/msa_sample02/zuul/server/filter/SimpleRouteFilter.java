package com.msa_sample02.zuul.server.filter;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class SimpleRouteFilter extends ZuulFilter {

	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public String filterType() {
		return "route";
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

	    log.debug(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    log.debug("I AM HITTING THE AUTH SERVER: " + request.getHeader("Authorization"));
        
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            
            log.debug(headerName + " : " + request.getHeader(headerName));
        }

	    return null;
	}

}
