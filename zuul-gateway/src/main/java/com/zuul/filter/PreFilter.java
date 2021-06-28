package com.zuul.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zuul.helper.JwtUtil;

@Component
public class PreFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreFilter.class);

	@Autowired
	private JwtUtil jwtUtil;
	
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
	    RequestContext ctx = RequestContext.getCurrentContext();
	    HttpServletRequest request = ctx.getRequest();
	    
	    log.info("Pre : " + request.getHeader("Authorization"));
	    String authorizationHeader = request.getHeader("Authorization");
	    if(authorizationHeader != null) {
	    	ctx.addZuulRequestHeader("UserName", jwtUtil.extractUsername(authorizationHeader));
	    }	    
	    log.info("PreFilter: " + String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
	    
	    return null;
	  }

}