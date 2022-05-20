package com.springboot.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration //component
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //모든 secruity에 대한 설정이 있음
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/api/board/**", "/", "/board/list") //요청이 들어오면
			.authenticated() //인증을 거쳐라
			.antMatchers("/api/v1/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/v1/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/v1/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			.anyRequest()
			.permitAll() //권한이 필요없다.
			.and()
			.formLogin() //parameter를 받아서 로그인하겠다
			.loginPage("/auth/signin") //로그인 페이지 get요청(view),페이지 띄어줌
			.loginProcessingUrl("/auth/signin") //로그인 post요청(PrincipalDetailService -> loadUserByUsername() 호출)
			.defaultSuccessUrl("/"); //로그인 되고나서 보내짐
			//springboot security filter
	}

}
