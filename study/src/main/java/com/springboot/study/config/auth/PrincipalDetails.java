package com.springboot.study.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.springboot.study.domain.user.User;

import lombok.Data;

@Data
public class PrincipalDetails implements UserDetails, OAuth2User{
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private  Map<String, Object> attributes;
	
	public PrincipalDetails(User user) {
		this.user = user;
	}
	
	public PrincipalDetails(User user, Map<String, Object> attributes) {
		this.user = user;
		this.attributes = attributes;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { //권한을 담고있는  Collection
		Role role = new Role();
		
		user.getRoleList();
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		user.getRoleList().forEach(r -> { //람다List
			System.out.println("권한: " + r);
			authorities.add(() -> r);
			});	
		
		authorities.forEach(r -> {System.out.println("리스트에 들어있는 권한" + r.getAuthority());});
		
		return authorities;
	}
	
	@Override
	public String getPassword() {

		return user.getPassword();
	}
	
	@Override
	public String getUsername() {

		return user.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() { //계정이 만료되었는지 확인
		
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() { //비밀번호가 지정한 횟수 이상 틀리면 잠김

		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() { //자격 증명이 만료가 되면 계정사용 불가

		return true;
	}
	
	@Override
	public boolean isEnabled() { //휴먼계정

		return true;
	}
	
	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	
	@Override
	public String getName() {
		return (String)attributes.get("name");
	}
	

}
