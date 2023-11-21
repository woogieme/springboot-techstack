package com.woogie.techstack.trace.threadlocal;

import org.junit.jupiter.api.Test;

import com.woogie.techstack.trace.threadlocal.code.FieldService;
import com.woogie.techstack.trace.threadlocal.code.ThreadLocalFieldService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadFieldServiceTest {

	private ThreadLocalFieldService fieldService = new ThreadLocalFieldService();

	@Test
	void field(){

		log.info("main start");

		Runnable userA = () -> {
			fieldService.logic("userA");
		};

		Runnable userB = () -> {
			fieldService.logic("userB");
		};

		Thread threadA = new Thread(userA);
		threadA.setName("thread-A");
		Thread threadB = new Thread(userB);
		threadB.setName("thread-B");

		threadA.start();
		//sleep(2000); //1초에 끝나는 코드에서  2초뒤에하니까 동시성 문제가 전혀없는 코드 (충분히 현재코드가 종료될때까지 기다리는 코드라)
		sleep(100); //동시성 문제생김 ( 0.1초로 설정하여 1초동안 실행되는 threadA코드가 끝나기도전에 threadB가 실행된다 (동시성 이슈 문제)
		threadB.start();
		sleep(2000); //메인 쓰레드 종료 대기
		log.info("main exist");
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


// Runnable userA = new Runnable() {
// 	@Override
// 	public void run() {
// 		fieldService.logic("userA");
// 	}
// };