package com.springboot.study.domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int user_code;
	private String email;
	private String name;
	private String username;
	private String oAuth2_username;
	private String password;
	private String roles; //ROLE_USER, ROLE_MANAGER, ROLE_ADMIN //ROLE: security
	private String provider;
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(",")); //split은 배열로 리턴
		}
		return new ArrayList<String>();
	}
}
