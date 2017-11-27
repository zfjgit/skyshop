package com.sitv.skyshop.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	protected void configure(HttpSecurity http) throws Exception {
		// http.authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated()
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();
		super.configure(http);
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		super.configure(auth);
	}
}
