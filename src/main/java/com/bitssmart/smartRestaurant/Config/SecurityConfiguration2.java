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

import com.bitssmart.smartRestaurant.Model.UserRoles;


//@Configuration
//@EnableWebSecurity
//@Order
public class SecurityConfiguration2 extends WebSecurityConfigurerAdapter{
//
// @Autowired
// private BCryptPasswordEncoder bCryptPasswordEncoder;
// 
// @Autowired
// private DataSource dataSource;
// 
// private final String DELIVERY_GUY_QUERY = "select email, password,is_enabled from delivery_guy where email=?";
// @Override
// protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//  auth.jdbcAuthentication()
//   .usersByUsernameQuery(DELIVERY_GUY_QUERY)
//   //.authoritiesByUsernameQuery("SELECT email, 4 FROM  user_roles WHERE email=?")
//   .dataSource(dataSource)
//   .passwordEncoder(bCryptPasswordEncoder);
//  System.out.println("SecurityConfiguration2 :::::::::::::");
// }
// 
// @Override
// protected void configure(HttpSecurity http) throws Exception{
//  http.csrf().disable().authorizeRequests()
//   .antMatchers("/","/register","/orders","/registerdelivery","/registerGuy","/loginDeliveryGuy","/deliveryGuy/**").permitAll()
//   .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/icon/**", "/fonts/**").permitAll().anyRequest()
//   .authenticated().and()
//   .formLogin().loginPage("/loginDeliveryGuy").loginProcessingUrl("/deliveryGuy/orders")
//   .defaultSuccessUrl("/deliveryGuy/orders",true)
//   .failureUrl("/loginDeliveryGuy?error=true").permitAll()
//   .usernameParameter("username")
//   .passwordParameter("password")
//   .and().logout()
//   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//   .logoutSuccessUrl("/");
// }
// 
//	/*
//	 * @Bean public PersistentTokenRepository persistentTokenRepository() {
//	 * JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
//	 * db.setDataSource(dataSource);
//	 * 
//	 * return db; }
//	 */
}