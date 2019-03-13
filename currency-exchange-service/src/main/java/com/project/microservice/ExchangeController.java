package com.project.microservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	private static final Logger logger = LoggerFactory.getLogger( ExchangeController.class );
	private static final String logPrefix = "<<<<< ExchangeController >>>>>  ";
	
	@Autowired
	ExchangeRepository repo;
	
	@Autowired
	private Environment env;
	
	@GetMapping( value="/currency-exchange/from/{from}/to/{to}" )
	
	public ExchangeValue retriveExchangeValue( @PathVariable String from,
											   @PathVariable String to )
	{
		//ExchangeValue value = new ExchangeValue( 1000L, "USD", "INR", BigDecimal.valueOf( 70 ) );
		logger.info( logPrefix + " inside retriveExchangeValue service, parameters are {} and {} ", from, to );
		
		ExchangeValue value = repo.findByFromAndTo( from, to ); 
				
		value.setPort( Integer.parseInt( env.getProperty( "local.server.port" ) ) );
		
		return value;
	}
}
