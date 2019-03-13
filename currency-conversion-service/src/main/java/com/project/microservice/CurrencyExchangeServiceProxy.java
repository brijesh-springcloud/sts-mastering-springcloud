package com.project.microservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient( name="currency-exchange-service", url="localhost:8000" ) // Disabled to enable Ribbon and added below
//@FeignClient( name="currency-exchange-service" ) // Disabled to enable Zull and added below. This name will be prefix to below
												 // Service interface method as we have now zuul server name and we need api service name.
@FeignClient( "zull-api-gateway-server" )
@RibbonClient( name="currency-exchange-service" )  

public interface CurrencyExchangeServiceProxy {

	//@GetMapping( value="/currency-exchange/from/{from}/to/{to}" ) See above comments
	
	@GetMapping( value="currency-exchange-service/currency-exchange/from/{from}/to/{to}" )
	public CurrencyConversionBean retriveExchangeValue( @PathVariable String from, @PathVariable String to );
}

