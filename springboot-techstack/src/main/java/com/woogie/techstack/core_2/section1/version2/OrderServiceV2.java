package com.woogie.techstack.core_2.section1.version2;

import org.springframework.stereotype.Service;

import com.woogie.techstack.core_2.section1.trace.TraceId;
import com.woogie.techstack.core_2.section1.trace.TraceStatus;
import com.woogie.techstack.core_2.section1.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV2 {

	private final OrderRepositoryV2 orderRepositoryV0;
	private final HelloTraceV2 trace;

	public void orderItem(TraceId traceId, String itemId) {

		TraceStatus status = null;
		try {
			status = trace.beginSync(traceId, "OrderService.request()");
			orderRepositoryV0.save(status.getTraceId(), itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
			//왜냐하면 trace.exception에서 멈추게된다면 로그 기능때문에 다시 못돌아올수있는
			//비지니스 로직을 건드는 행위이므로 throw e를 해줘야함
		}
	}

}
