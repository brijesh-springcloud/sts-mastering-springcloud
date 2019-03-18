package com.project.zuul.apigateway;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TestHandlerInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger( this.getClass() );
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		logger.info("<<<<< " + this.getClass() + ">>>>>>  preHandle ");
		
		logger.info( "<<<< Token >>>> " + request.getHeader("Authorization") );
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable ModelAndView modelAndView) throws Exception {
		
		logger.info("<<<<< " + this.getClass() + ">>>>>>  postHandle ");
		
		logger.info( "<<<< Token >>>> " + request.getHeader("Authorization") );
		
		 
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		
		logger.info("<<<<< " + this.getClass() + ">>>>>>  afterCompletion ");
		
		logger.info( "<<<< Token >>>> " + request.getHeader("Authorization") );
	}
}
