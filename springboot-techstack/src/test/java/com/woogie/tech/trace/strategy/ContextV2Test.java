package com.woogie.tech.trace.strategy;

import org.junit.jupiter.api.Test;

import com.woogie.tech.trace.strategy.code.strategy.ContextV2;
import com.woogie.tech.trace.strategy.code.strategy.StrategyLogic1;
import com.woogie.tech.trace.strategy.code.strategy.StrategyLogic2;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2Test {

	/*
	* 전략 패턴 적용
	 */
	@Test
	void strategyV2(){
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogic1());
		context.execute(new StrategyLogic2());
	}

	/*
	* 전략 패턴 익명 내부 클래스
	* */
	@Test
	void strategyV3(){
		ContextV2 context = new ContextV2();
		context.execute(()->log.info("비지니스 로직1 실행"));
		context.execute(()->log.info("비지니스 로직2 실행"));

	}
}
