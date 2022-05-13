package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.springboot.study.handler.ex.CustomValidationApiException;

/*
 * Aspect : 여러 핵심 기능에 적용될 과심사 모듈을 의미한다. Aspect에는 구체적인 기능을 구현한 Advice와 Advice가 어디에서 적용될지 
 * 			PointCut을 포함하고 있다.
 * 
 * Target : 공통 기능을 부여할 대상. 즉, 핵심기능을 담당하는 비즈니스 로직이고, 어떤 관심사들과도 관계를 맺지 않는닫.
 * 
 * Advice : 공통 기능을 담은 구현체. Advice는 Aspect가 무엇을 언제 적용할 지를 정의하는것.
 * 
 * PoinCut : 공통 기능이 적용될 대상을 결정하는 것.
 * 
 * JoinPoint : Advice가 적용될 지점을 의미한다. Spring에서는 메서드에서만 제공이 된다.
 */

@Aspect
@Component
public class ValidationAdvice {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ValidationAdvice.class);
	
	@Around("execution(* com.springboot.study.web.controller.api.*Controller.*(..))") //pointcut //* 앞에 메소드가능(private, public)생략가능//..:하위폴더
	public Object apiAdvice(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
		Object[] args = proceedingJoinPoint.getArgs();
		for(Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg; //다운캐스팅
				if(bindingResult.hasErrors()) {
					Map<String, String> errorMap = new HashMap<String, String>();
					for(FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					LOGGER.info("Validation AOP 실행됨");
					
					throw new CustomValidationApiException("유효성 검사 실패", errorMap); //throw 예외발생
				}
			}
		}

		
		
		return proceedingJoinPoint.proceed(); //filter chain  전처리
	}
}
