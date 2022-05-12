package com.springboot.study.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class AccountReqDto {
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	private String name;
	
}
