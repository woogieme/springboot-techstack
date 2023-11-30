package com.woogie.tech.core2.section2.version3;

import org.springframework.stereotype.Repository;

import com.woogie.tech.core2.trace.TraceStatus;
import com.woogie.tech.core2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

// 상품이 저장하는데 1초 정도 걸린다
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV3 {

	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임

	private final LogTrace trace;
	public void save(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderRepository.request()");

			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}

			sleep(1000);

			trace.end(status);

		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
		}
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}