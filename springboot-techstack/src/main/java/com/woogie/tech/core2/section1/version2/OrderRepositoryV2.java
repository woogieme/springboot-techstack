package com.woogie.tech.core2.section1.version2;

import org.springframework.stereotype.Repository;

import com.woogie.tech.core2.section1.trace.TraceId;
import com.woogie.tech.core2.section1.trace.TraceStatus;
import com.woogie.tech.core2.section1.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

// 상품이 저장하는데 1초 정도 걸린다
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV2 {

	private final HelloTraceV2 trace;

	public void save(TraceId traceId, String itemId) {

		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderRepository.request()");

			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}

			sleep(1000);

			trace.end(status);

		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
			//왜냐하면 trace.exception에서 멈추게된다면 로그 기능때문에 다시 못돌아올수있는
			//비지니스 로직을 건드는 행위이므로 throw e를 해줘야함
		}

		//저장 로직

	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
// 만약에 상품아이디가 ex로 넘어가면 예외 발생해서 repository에서 저장하는것이 아닌
// 예외로 처리되어 저장이 되지않고 에러가 터진다