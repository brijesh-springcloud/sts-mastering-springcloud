package com.project.zuul.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import brave.sampler.Sampler;


@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
@EnableOAuth2Sso
public class ZullApigatewayServerApplication {

	private static final Logger logger = LoggerFactory.getLogger( ZullApigatewayServerApplication.class );
	
	public static void main(String[] args) {
		SpringApplication.run(ZullApigatewayServerApplication.class, args);
	}

	@Bean
	public Sampler defaultSampler()
	{
		return Sampler.ALWAYS_SAMPLE;
	}
	
	// Added for Globally disabling CORS
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
	
	/*
	// Added to call the Request Interceptor but some how not working....
	@Bean
    public RequestInterceptor getUserFeignClientInterceptor() {
        
		logger.info( "<<<<< ZullApigatewayServerApplication >>>> UserFeignClinetInterceptor has registed ! " );
		return new UserFeignClientInterceptor();
    }
	
	// Added to call the Request Interceptor but some how not working....
	 @Bean
	 public WebMvcConfigurerAdapter adapter() {
	    return new WebMvcConfigurerAdapter() {
	        @Override
	        public void addInterceptors(InterceptorRegistry registry) {
	            registry.addInterceptor(new TestHandlerInterceptor());
	            super.addInterceptors(registry);
	        }
	    };
	 }
	 */
}
