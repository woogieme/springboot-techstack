package com.woogie.tech.trace.strategy;

import org.junit.jupiter.api.Test;

import com.woogie.tech.trace.strategy.code.template.Callback;
import com.woogie.tech.trace.strategy.code.template.TimeLogTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateCallbackTest {

	//별도의 클래스를 만들어 사용해도되지만, 콜백을 사용할경우 익명 내부 클래스나 람다를 사용하는것이 편리하다.
	//물론 여러곳에서 함께사용되는 경우 재사용을 위해서 콜백을 별도의 클래스로 만들어도 된다.

	/*
	*
	* 템플릿 콜백 패턴
	* */
	@Test
	void callbackV1(){
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(new Callback() {
			@Override
			public void call() {
				log.info("비지니스 로직1 실행");
			}
		});

		 template.execute(new Callback() {
			 @Override
			 public void call() {
				 log.info("비지니스 로직2 실행");
			 }
		 });
	}

	@Test
	void callbackV2(){
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(() -> log.info("비지니스 로직1 실행"));
		template.execute(() -> log.info("비지니스 로직2 실행"));
	}
}
