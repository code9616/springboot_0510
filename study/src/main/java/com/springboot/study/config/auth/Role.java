package com.springboot.study.config.auth;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{
	private String role;
	
	@Override
	public String getAuthority() {
		
		return null;
	}

}
