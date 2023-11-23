package com.woogie.techstack.trace.strategy;

import org.junit.jupiter.api.Test;

import com.woogie.techstack.trace.strategy.code.strategy.ContextV1;
import com.woogie.techstack.trace.strategy.code.strategy.Strategy;
import com.woogie.techstack.trace.strategy.code.strategy.StrategyLogic1;
import com.woogie.techstack.trace.strategy.code.strategy.StrategyLogic2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV1Test {
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

	//클래스 만들어서 사용
	@Test
	void strategyV1(){
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 contextV1 = new ContextV1(strategyLogic1);
		contextV1.execute();

		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 contextV2 = new ContextV1(strategyLogic2);
		contextV2.execute();
	}

	//익명 내부 클래스사용
	@Test
	void strategyV2(){
		Strategy strategyLogic1 = new Strategy() {
			@Override
			public void call() {
				log.info("비지니스 로직1 실행");
			}
		};
		ContextV1 contextV1 = new ContextV1(strategyLogic1);
		log.info("strateLogic1={}",strategyLogic1.getClass());
		contextV1.execute();
		Strategy strategyLogic2 = new Strategy() {
			@Override
			public void call() {
				log.info("비지니스 로직2 실행");
			}
		};
		ContextV1 contextV2 = new ContextV1(strategyLogic2);
		log.info("strateLogic2={}",strategyLogic2.getClass());
		contextV2.execute();

	}

	//코드 간결하게
	@Test
	void strategyV3(){
		ContextV1 contextV1 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비지니스 로직1 실행");
			}
		});
		contextV1.execute();


		ContextV1 contextV2 = new ContextV1(new Strategy() {
			@Override
			public void call() {
				log.info("비지니스 로직2 실행");
			}
		});
		contextV2.execute();

	}

	//코드 간결하게 2
	@Test
	void strategyV4(){
		ContextV1 contextV1 = new ContextV1(() -> log.info("비지니스 로직1 실행"));
		contextV1.execute();


		ContextV1 contextV2 = new ContextV1(() -> log.info("비지니스 로직2 실행"));
		contextV2.execute();

	}
}
