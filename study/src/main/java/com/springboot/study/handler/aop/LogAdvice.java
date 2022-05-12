package com.springboot.study.handler.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("within(com.springboot.study..*)")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{
		long startAt = System.currentTimeMillis();
		
		LOGGER.info("------Advice Call: {} ({}) = {}", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), "데이터");
		
		Object result = pjp.proceed();
		
		long endAt = System.currentTimeMillis();
		
		LOGGER.info("------Advice Call: {} ({}) = {}", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), "데이터", endAt );
		
		return result;
	}
}
