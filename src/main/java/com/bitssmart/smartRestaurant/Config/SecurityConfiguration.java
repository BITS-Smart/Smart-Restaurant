package com.bitssmart.smartRestaurant.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


//@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

 @Autowired
 private BCryptPasswordEncoder bCryptPasswordEncoder;
 
 @Autowired
 private DataSource dataSource;
 
 private final String USERS_QUERY = "select email, password,is_enabled from users where email=?";
 @Override
 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
  auth.jdbcAuthentication()
   .usersByUsernameQuery(USERS_QUERY)
   .authoritiesByUsernameQuery(
           "SELECT email, user_roles FROM users WHERE email=?")
   .dataSource(dataSource)
   .passwordEncoder(bCryptPasswordEncoder);
  System.out.println("SecurityConfiguration1 :::::::::::::");
 }
 
 @Override
 protected void configure(HttpSecurity http) throws Exception{
  http.csrf().disable().authorizeRequests()
   .antMatchers("/","/register","/index","/registerdelivery","/registerGuy","/loginDeliveryGuy","/deliveryGuy/**").permitAll()
   .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/icon/**", "/fonts/**").permitAll().anyRequest()
   .authenticated().and()
   .formLogin().loginPage("/login").loginProcessingUrl("/home/index")
   .defaultSuccessUrl("/home/index",true)
   .failureUrl("/login?error=true").permitAll()
   .usernameParameter("username")
   .passwordParameter("password")
   .and().logout()
   .logoutRequestMatcher(new AntPathRequestMatcher("/singout"))
   .logoutSuccessUrl("/login");
 }
 
 @Bean
 public PersistentTokenRepository persistentTokenRepository() {
  JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
  db.setDataSource(dataSource);
  
  return db;
 }
}