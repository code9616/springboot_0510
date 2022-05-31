package com.springboot.study.handler.aop;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAdvice {
	private static final Logger LOGGER = LoggerFactory.getLogger(LogAdvice.class);//
	
	@Pointcut("within(com.springboot.study.test..*)")
	private void pointcut() {
		
	}
	
	@Around("pointcut()") //within 그 패키지 내의 모든 메소드적용
	public Object logging(ProceedingJoinPoint pjp) throws Throwable{
		//long startAt = System.currentTimeMillis();//현재시간
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Map<String, Object> params = getPrams(pjp);
		
		LOGGER.info("------Advice Call: {} ({}) = {}", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), params);
		
		Object result = pjp.proceed();
		
		//long endAt = System.currentTimeMillis();//끝나는시간
		stopWatch.stop();
		LOGGER.info("------Advice End: {} ({}) = {} ({}ms)", pjp.getSignature().getDeclaringTypeName(),
				pjp.getSignature().getName(), result, stopWatch.getTotalTimeMillis()); //오는데 걸린 시간 //result 메서드결과
		
		return result;
	}
	
	public Map<String, Object> getPrams(ProceedingJoinPoint pjp){
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		Object[] args = pjp.getArgs();
		String[] argNames = ((CodeSignature)pjp.getSignature()).getParameterNames(); //다운캐스팅 getParameterNames사용가능
		for(int i = 0; i < args.length; i++) {
			params.put(argNames[i], args[i]);
		}
		return params;
	}
	
}
