package com.woogie.techstack.core_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woogie.techstack.core_2.trace.logtrace.LogTrace;
import com.woogie.techstack.core_2.trace.logtrace.ThreadLocalLogTrace;

@Configuration //Component가있음 자동으로 컴포넌트 스캔
public class LogTraceConfig {

	//스프링 빈으로 등록됨 싱글톤으로 등록이되는것임
	//즉 인스턴스 하나로 등록이된다는 말임
	// @Bean
	// public LogTrace logTrace(){
	// 	return new FieldLogTrace();
	// }

	@Bean
	public LogTrace logTrace(){
		return new ThreadLocalLogTrace();
	}
}
