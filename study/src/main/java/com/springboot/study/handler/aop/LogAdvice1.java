package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAdvice1 {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);
	
	@Around("within(com.springboot.study..*)")
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{
		long startAt = System.currentTimeMillis();
		
		Map<String, Object> params = getPrams(pjp);
		
		LOGGER.info("-----Advice Call: {}({}) = {}", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), params);
		
		Object result = pjp.proceed();
		
		long endAt = System.currentTimeMillis();
		
		LOGGER.info("-----Advice Call: {}({}) = ({}ms)", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), result, endAt - startAt);
		
		return result;
	}
	private Map<String, Object> getPrams(ProceedingJoinPoint pjp){
		Map<String, Object> params = new HashMap<String, Object>();
		
		Object[] args = pjp.getArgs();
		String[] argNames = ((CodeSignature)pjp.getSignature()).getParameterNames();
		
		for(int i = 0; i < args.length; i++) {
			params.put(argNames[i], args[i]);
		}
		return params;
	}
}
