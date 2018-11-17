package com.sit.int202.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
@EnableJpaAuditing
@EnableOAuth2Sso

public class BackendApplication extends WebSecurityConfigurerAdapter{

	public static void main(String[] args){
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
    protected void configure (HttpSecurity http) throws Exception {
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");

        http
        .csrf().disable().antMatcher("/**")
        .authorizeRequests()
			.antMatchers("/creditcard","/login").authenticated()
			.anyRequest().permitAll()

            .and().logout().logoutSuccessUrl("/").permitAll();
    }
}
