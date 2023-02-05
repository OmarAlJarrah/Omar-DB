package com.omar.config.security;

import com.omar.file.Reader;
import com.omar.model.access.impl.Role;
import com.omar.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.File;

@Configuration
@EnableWebSecurity
public class BasicAuthConfig {
  @Autowired
  private Reader reader;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Bean
  @Order(1)
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .authorizeRequests()
        .antMatchers("*/users/*")
        .hasRole(Role.ROLE_ADMIN.role)
        .anyRequest()
        .authenticated()
        .and()
        .cors().and().csrf().disable()
        .httpBasic()
        .and()
        .userDetailsService(userDetailsService).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
  }
}
