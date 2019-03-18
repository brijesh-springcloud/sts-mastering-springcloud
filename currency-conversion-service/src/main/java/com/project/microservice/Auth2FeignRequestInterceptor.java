package com.project.microservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class Auth2FeignRequestInterceptor implements RequestInterceptor {

	private static final Logger logger = LoggerFactory.getLogger( "Auth2FeignRequestInterceptor" );
	
	public Auth2FeignRequestInterceptor()
	{
		logger.info( "  >>>>>>>> <<<<<Auth2FeignRequestInterceptor>>>>>  <<<<<<<<<<<<<<<< ");
	}
	
	@Override
	public void apply(RequestTemplate requestTemplate ) {
		
		logger.info( "<<<<<Auth2FeignRequestInterceptor>>>>> "+ requestTemplate.request().headers().get( "Authorization" ) );
		
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (request == null) {
            return;
        }
        String language = request.getHeader( "Authorization" );
        if (language == null) {
        	logger.info( "<<<<<Auth2FeignRequestInterceptor>>>>> Authorization token not found ..... ");
            return;
        }
        requestTemplate.header( "Authorization" , language);
	}

} 
