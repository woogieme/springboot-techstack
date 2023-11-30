package com.woogie.tech.core2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.woogie.tech.core2.section4.v1.Sec4OrderControllerImpl;
import com.woogie.tech.core2.section4.v1.Sec4OrderControllerV1;
import com.woogie.tech.core2.section4.v1.Sec4OrderRepositoryImpl;
import com.woogie.tech.core2.section4.v1.Sec4OrderRepositoryV1;
import com.woogie.tech.core2.section4.v1.Sec4OrderServiceV1;
import com.woogie.tech.core2.section4.v1.Sec4OrderServiceV1Impl;

@Configuration
public class AppV1Config {

	@Bean
	public Sec4OrderControllerV1 sec4OrderControllerV1() {
		return new Sec4OrderControllerImpl(sec4OrderServiceV1());
	}

	@Bean
	public Sec4OrderServiceV1 sec4OrderServiceV1() {
		return new Sec4OrderServiceV1Impl(sec4OrderRepositoryV1());
	}

	@Bean
	public Sec4OrderRepositoryV1 sec4OrderRepositoryV1() {
		return new Sec4OrderRepositoryImpl();
	}
}
