package com.project.zuul.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;

@Configuration
@EnableResourceServer
public class GatewayConfig extends ResourceServerConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger( GatewayConfig.class );
	
	@Autowired
	private TokenStore tokenStore; 

	@Override
    public void configure(final HttpSecurity http) throws Exception 
	{
		logger.info( " <<<<< GatewayConfig >>>>>   Verifying URLs if they required Authentication/Authorizations !!!" );
		http 
		//.requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
		.authorizeRequests()
		.antMatchers("/spring-oauth-server/oauth/**")
		.permitAll()
		.antMatchers("/**")
		.authenticated();
		
		
    }
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception 
	{
		logger.info( " <<<<< GatewayConfig >>>>>   Setting Resource ID as USER_ADMIN_RESOURCE !!!" );
		
		resources.resourceId("USER_ADMIN_RESOURCE").tokenStore(tokenStore);
	}
}
