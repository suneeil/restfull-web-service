package com.learn.sprintboot.restfulwebservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //Step-1 All requests are authenticated
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //Step-2 If a request is not authenticated , a web page is shown
        //For REST API we want to enable basic authentication
        httpSecurity.httpBasic(Customizer.withDefaults());
        //Disable CSRF so that we can send post requests
        httpSecurity.csrf().disable();
        return httpSecurity.build();
    }
}
