package com.springboot.study.web.controller.api.data;

import lombok.Data;

@Data
public class User {
	private int usercode;
	private String username;
	private String password;
	private String email;
	private String name;
	
	public User() {
		usercode=20220001;
		username= "aaa";
		password= "a12345678!";
		email = "aaa@gamil.com";
		name = "김지훈";
	}
}
