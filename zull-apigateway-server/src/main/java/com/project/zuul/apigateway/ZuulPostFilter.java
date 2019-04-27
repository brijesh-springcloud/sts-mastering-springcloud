package com.project.zuul.apigateway;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ZuulPostFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger( ZuulPostFilter.class );
	private static final String logPrefix = "<<<<< ZuulPostFilter >>>>>  ";
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException 
	{
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		
		logger.info( logPrefix + " request : {} , Request URL {} ", request, request.getRequestURI() );
		
		logger.info( logPrefix + " Authorization Header ::: " + request.getHeader( "Authorization" ) );
		
		logger.info( logPrefix + " Request Parameters ");
		request.getParameterMap().forEach( (a,b) -> logger.info( logPrefix + a + " : " + b ) ); 

		
		return null;
	}

	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "post";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
