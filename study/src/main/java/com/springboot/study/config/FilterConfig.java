package com.springboot.study.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springboot.study.filter.TestFilter1;
import com.springboot.study.filter.TestFilter2;

@Configuration
public class FilterConfig {
	
	@Bean
	public FilterRegistrationBean<TestFilter1> filter1(){
		FilterRegistrationBean<TestFilter1> bean = new FilterRegistrationBean<TestFilter1>(new TestFilter1());
		bean.addUrlPatterns("/*");
		bean.setOrder(1);
		return bean;
	}
	
	@Bean
	public FilterRegistrationBean<TestFilter2> filter2(){
		FilterRegistrationBean<TestFilter2> bean = new FilterRegistrationBean<TestFilter2>(new TestFilter2());
		bean.addUrlPatterns("/*");
		bean.setOrder(0); //필터는 낮은것부터 실행 (setOrder)
		return bean;
	}
}
