package com.project.limits;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.project.limits.beans.LimitConfig;
import com.project.limits.config.Configuration;

@RestController
public class LimitsConfigController {

	private static final Logger logger = LoggerFactory.getLogger( LimitsConfigController.class );
	private static final String logPrefix = "<<<<< LimitsConfigController >>>>> ";
	
	@Autowired
	private Configuration config;
	
	@GetMapping( "/limits" )
	public LimitConfig retriveLimitsFromConfig()
	{
		logger.info( logPrefix + " /limits >> retriveLimitsFromConfig() " );
		return new LimitConfig( config.getMaximum(), config.getMinimum() );
	}
	
	@GetMapping( "/fault-tolerance-test" )
	@HystrixCommand( fallbackMethod="testFaultToleranceFallback" )
	public LimitConfig testFaultTolerance()
	{
		logger.info( logPrefix + " /fault-tolerance-test >> testFaultTolerance() " );
		
		throw new RuntimeException("This sevice is current not available !!!");
	}
	
	public LimitConfig testFaultToleranceFallback()
	{
		logger.info( logPrefix + " /fault-tolerance-test (fallback) >> testFaultToleranceFallback() " );
		
		return new LimitConfig(999,999);
	}
	
	
}
