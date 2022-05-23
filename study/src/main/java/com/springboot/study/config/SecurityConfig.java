package com.springboot.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.study.config.oauth2.PrincipalOauth2UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
// 기존의 WebSecurityConfigurerAdapter의 설정을 비활성화 시키고 현재 클래스(SecurityConfig)의 설정을 따르겠다.
@Configuration //component개념, 이 어노테이션을 사용해야지 Bean을사용가능 ioc등록
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //모든 secruity에 대한 설정이 있음
	
	private final PrincipalOauth2UserService principalOauth2UserService;
	
	@Bean //ioc등록
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf
		http.authorizeRequests() //인증요청
			.antMatchers("/api/board/**", "/", "/board/list") //요청이 들어오면 //URI 지정
			.authenticated() //인증을 거쳐라
			.antMatchers("/api/v1/user/**")
			.access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/v1/manager/**")
			.access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
			.antMatchers("/api/v1/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			.anyRequest() //다른 모든 요청들
			.permitAll() //모두에게 권한을 주겠다.(권한이 필요없다.)
			.and()
			.formLogin() //parameter를 받아서 로그인하겠다
			.loginPage("/auth/signin") //로그인 페이지 get요청(view),페이지 띄어줌
			.loginProcessingUrl("/auth/signin") //로그인 post요청(PrincipalDetailService -> loadUserByUsername() 호출)
			.defaultSuccessUrl("/") //로그인 되고나서 보내짐
			.and()
			.oauth2Login()
			.loginPage("/auth/signin")
			.userInfoEndpoint()
			.userService(principalOauth2UserService)
			.and()
			.defaultSuccessUrl("/");
			//springboot security filter jwt
	}

}
