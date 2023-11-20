package com.woogie.techstack.core_2.section1.version1;

import org.springframework.stereotype.Service;

import com.woogie.techstack.core_2.section1.trace.TraceStatus;
import com.woogie.techstack.core_2.section1.trace.hellotrace.HelloTraceV1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV1 {

	private final OrderRepositoryV1 orderRepositoryV0;
	private final HelloTraceV1 trace;

	public void orderItem(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.request()");
			orderRepositoryV0.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
			//왜냐하면 trace.exception에서 멈추게된다면 로그 기능때문에 다시 못돌아올수있는
			//비지니스 로직을 건드는 행위이므로 throw e를 해줘야함
		}

		orderRepositoryV0.save(itemId);

	}

}
