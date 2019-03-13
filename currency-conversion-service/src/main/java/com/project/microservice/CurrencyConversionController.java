package com.project.microservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	private static final Logger logger = LoggerFactory.getLogger( CurrencyConversionController.class );
	private static final String logPrefix = "<<<<<  CurrencyConversionController  >>>>>  ";
	
	@Autowired
	CurrencyExchangeServiceProxy proxy;

	@GetMapping( "/from/{from}/to/{to}/quantity/{quantity}" )
	public CurrencyConversionBean convertCurrency( @PathVariable String from, @PathVariable String to, 
												   @PathVariable BigDecimal quantity )
	{
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put( "from", from );
		parameters.put( "to", to );
		
		// Calling third party service using RestTemplate.
		
		ResponseEntity<CurrencyConversionBean> response =
		new RestTemplate().getForEntity( "http://localhost:8000/currency-exchange/from/{from}/to/{to}/", 
											CurrencyConversionBean.class, parameters );
		
		CurrencyConversionBean bean = response.getBody();
		bean.setQuantity( quantity );
		bean.setTotalCalculatedAmount( bean.getConversionMultiple().multiply( quantity ) );
		
		return bean;
	}
	
	@GetMapping( "/feign/from/{from}/to/{to}/quantity/{quantity}" )
	public CurrencyConversionBean convertCurrencyFeign( @PathVariable String from, @PathVariable String to, 
												   @PathVariable BigDecimal quantity )
	{
		logger.info( logPrefix + " inside convertCurrencyFeign calling Exchange Service with parameters {} and {} ", from, to );
		
		CurrencyConversionBean bean = proxy.retriveExchangeValue(from, to);
		
		bean.setQuantity( quantity );
		bean.setTotalCalculatedAmount( bean.getConversionMultiple().multiply( quantity ) );
		
		return bean;
	}
}
