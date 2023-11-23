package com.woogie.techstack.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
* 필드에 전략을 보관하는 방식
* */

//변하지 않은 로직을 가지고있는 템플릿 역할을 하는 코드(클래스)이다. 전략패턴에서는 이것을 컨텍스트(문맥)이라고한다.
//쉽게 이야기하자면 컨텍스트는 크게 변하지 않지만, 그 문맥 속에서 'strategy'를 통해 일부 전략이 변경된다 생각하면 된다.
//코드의 장점 ContextV1도 변하지 않는점이 있지만, Strategy를 주입받아 사용하기때문에 Strategy의 변경이 존재하여도 Context에도
//문제가 생기지않는다. -> 의존관계 주입이 전략 패턴이다.

@Slf4j
public class ContextV1 {

	private Strategy strategy;

	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}
	public void execute(){
		long startTime = System.currentTimeMillis();
		//비지니스 로직 실행
		strategy.call(); //위임
		//비지니스 로직 종료
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("resultTime={}", resultTime);
	}
}

