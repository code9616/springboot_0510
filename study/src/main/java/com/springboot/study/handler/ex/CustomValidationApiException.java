package com.springboot.study.handler.ex;

import java.util.Map;

import lombok.Getter;

@Getter
public class CustomValidationApiException extends RuntimeException{
	/*
	 * 객체를 구분할 때 사용!
	 */
	 private static final long serialVersionUID = 1L;
	 
	 private Map<String, String> errorMap;
	 
	 private CustomValidationApiException(String message) {
		 super(message);
	 }
	 
	 public CustomValidationApiException(String message, Map<String, String> errorMap) {
		 super(message);
		 this.errorMap = errorMap;
	 }

}
