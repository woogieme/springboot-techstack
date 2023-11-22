package com.woogie.techstack.trace.template;

import org.junit.jupiter.api.Test;

import com.woogie.techstack.trace.template.code.AbstractTemplate;
import com.woogie.techstack.trace.template.code.SubClassLogic1;
import com.woogie.techstack.trace.template.code.SubClassLogic2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethodTest {

	@Test
	void templateMethodV0(){
		logic1();
		logic2();
	}

	private void logic1() {
		long startTime = System.currentTimeMillis();
		//비지니스 로직 실행
		log.info("비지니스 로직1 실행");
		//비지니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
	private void logic2() {
		long startTime = System.currentTimeMillis();
		//비지니스 로직 실행
		log.info("비지니스 로직2 실행");
		//비지니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}


	/*
	*
	* 템플릿 메서드 패턴 적용
	* */
	@Test
	void templateMethod1(){
		AbstractTemplate template1 = new SubClassLogic1();
		template1.execute();
		AbstractTemplate template2 = new SubClassLogic2();
		template2.execute();
	}

	/*
	* 익명 내부 클래스
	* */
	@Test
	void templateMethod2(){
		AbstractTemplate template1 = new AbstractTemplate() {
			@Override
			protected void call() {
				log.info("비지니스 로직 1실행");
			}
		};
		log.info("클래스 이름1={}",template1.getClass());
		template1.execute();
		AbstractTemplate template2 = new AbstractTemplate() {
			@Override
			protected void call() {
				log.info("비지니스 로직 2실행");
			}
		};
		log.info("클래스 이름2={}",template2.getClass());

		template2.execute();
	}

}
