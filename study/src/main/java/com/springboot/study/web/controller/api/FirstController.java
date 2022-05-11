package com.springboot.study.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //데이터만 리턴(view를 리턴할 수 없음) -> responsebody(그렇기때문에 데이터가 text로 넘어감)가 붙은 controller
public class FirstController {
	
	@GetMapping("/hello") //@requestMapping(value = "/hello", method = requsetMethod.get)을 줄여씀
	public String hello() {
		return "hello";
	}
	
	//@Get/PostMapping/PutMapping/DelteMapping 존재 
	
		/*
		 * 요청 리소스 : add(덧셈), sub(뺄셈), mul(곱셈), div(나눗셈)
		 * 파라미터 : a,b
		 * 
		 * div 파라미터가 둘중 하나라도 0이면 '0으로 계산할 수 없습니다.'
		 */
	
	@GetMapping("/add")
	public String add(@RequestParam("v") List<Integer> values) {
		int result = 0;
		for(int i :values) {
			result += i;
		}
		return Integer.toString(result);
	}
	
	@GetMapping("/sub")
	public int sub(int a,int b) {
		return a-b;
	}
	
	@GetMapping("/mul")
	public int mul(int a,int b) {
		return a*b;
	}
	
	@GetMapping("/div")
	public String div(int a,int b) {
		if(a==0 || b==0) {
			return "0으로 계산할 수 없음";
		}
		return Integer.toString(a/b);
	}
}
