package com.project.zuul.apigateway.config;

//Configuration

public class WebSecurityConfig //extends OAuth2SsoDefaultConfiguration 
{/*

	
	public WebSecurityConfig(ApplicationContext applicationContext) {
		super(applicationContext);
	}

	private OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().exceptionHandling()
				.authenticationEntryPoint( entryPoint ).and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().anyRequest()
				.authenticated();

		//httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

		httpSecurity.headers().frameOptions().sameOrigin() // H2 Console Needs this setting
				.cacheControl(); // disable caching
	}


	public OAuth2AuthenticationEntryPoint getEntryPoint() {
		return entryPoint;
	}


	public void setEntryPoint(OAuth2AuthenticationEntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}
	
	
*/}
