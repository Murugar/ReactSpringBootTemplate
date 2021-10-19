package com.iqmsoft.boot.react.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Value("${jwt.route.authentication.path}")
	private String authPath;

	// @Autowired
	// private UserDetailsService userDetailsService;

	/*@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin")
				.password(bcrypt().encode("admin")).roles("USER", "ADMIN").and().withUser("user")
				.password(bcrypt().encode("user")).roles("USER");

		auth.userDetailsService(userDetailsService());

	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/static/**").permitAll() // Allow anyone to access static assets
				.antMatchers("/rest/" + authPath).permitAll() // Allow anyone to make an auth attempt
				.anyRequest().permitAll(); // Permit all and secure by endpoint

		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

		http.headers().cacheControl();

	}

	@Bean
	public JwtAuthenticationEntryPoint authenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint();
	}

	@Bean
	public BCryptPasswordEncoder bcrypt() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password(bcrypt().encode("user")).roles("USER").build());
		manager.createUser(
				User.withUsername("admin").password(bcrypt().encode("admin")).roles("USER", "ADMIN").build());
		return manager;

	}

	/*
	 * @Bean public UserDetailsService userDetailsService() {
	 * InMemoryUserDetailsManager uds = new InMemoryUserDetailsManager(new
	 * HashSet<>());
	 * 
	 * /*try {
	 * 
	 * 
	 * if(!uds.userExists("admin")) { uds.createUser(new JwtUser("admin", new
	 * BCryptPasswordEncoder().encode("admin"), Arrays.asList(new
	 * SimpleGrantedAuthority("ROLE_ADMIN")), true)); } if(!uds.userExists("user"))
	 * { uds.createUser(new JwtUser("user", new
	 * BCryptPasswordEncoder().encode("user"), Arrays.asList(new
	 * SimpleGrantedAuthority("ROLE_USER")), true)); } } catch(Exception e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * return uds; }
	 */
}
