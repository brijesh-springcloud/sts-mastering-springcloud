package com.project.zuul.apigateway.config;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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
		//.requestMatcher(new RequestHeaderRequestMatcher("Authorization"))
		
		http 
		//configure CORS -- uses a Bean by the name of     corsConfigurationSource (see method below)
        //CORS must be configured prior to Spring Security
        .cors().and()
		.csrf().disable() // Disabled as request come from any host, otherwise this will block giving cors error
		.authorizeRequests()
		.antMatchers("/spring-oauth-server/oauth/**")
		.permitAll()
		.antMatchers("/**")
		.authenticated();
		
		
    }
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new     UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin(CorsConfiguration.ALL);
        config.setAllowedHeaders(Collections.singletonList(CorsConfiguration.ALL));
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception 
	{
		logger.info( " <<<<< GatewayConfig >>>>>   Setting Resource ID as USER_ADMIN_RESOURCE !!!" );
		
		resources.resourceId("USER_ADMIN_RESOURCE").tokenStore(tokenStore);
	}
}
