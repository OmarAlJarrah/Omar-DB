package com.omar.config.security;

import com.omar.file.Reader;
import com.omar.model.access.impl.User;
import com.omar.model.db.impl.metadata.Id;
import com.omar.model.exception.RecordNotFoundException;
import com.omar.model.exception.TableNotFoundException;
import com.omar.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

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
        .authorizeRequests().antMatchers("*/*/*").permitAll().and().cors().and().csrf().disable();
//        .anyRequest()
//        .authenticated()
//
//        .and()
//        .httpBasic()
//        .and()
//        .userDetailsService(userDetailsService).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    return httpSecurity.build();
  }

  @Bean
  public PasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder(10);
  }


}
