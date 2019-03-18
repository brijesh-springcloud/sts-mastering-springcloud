package com.project.zuul.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ZuulHandlerInterceptorConfig extends InstantiationAwareBeanPostProcessorAdapter  {

	private final Logger logger = LoggerFactory.getLogger( this.getClass() );
	 
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException 
	{
	
		//logger.info( "ZuulHandlerInterceptorConfig >> postProcessBeforeInitialization " );
		
		if (bean instanceof ZuulHandlerMapping) {

			ZuulHandlerMapping zuulHandlerMapping = (ZuulHandlerMapping) bean;
			zuulHandlerMapping.setInterceptors( new TestHandlerInterceptor() );
			zuulHandlerMapping.setInterceptors( new UserFeignClientInterceptor() );
            
            logger.info(" ZuulHandlerInterceptorConfig >> postProcessBeforeInitialization >> testHandler added  ");
        }
		
		return super.postProcessBeforeInitialization(bean, beanName);
		//return bean;
	}
		
}
