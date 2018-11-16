package com.sit.int202.backend.UserProfile;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class FacebookController extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .antMatcher("/**")
        .authorizeRequests()
            .antMatchers("/creditcard","/login","/webjars/**").permitAll()
        // .anyRequest()
        //     .authenticated()
        .and().logout().logoutSuccessUrl("/").permitAll();
    }
}