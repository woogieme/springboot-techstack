package com.woogie.tech.core2.section2.version3;

import org.springframework.stereotype.Service;

import com.woogie.tech.core2.trace.TraceStatus;
import com.woogie.tech.core2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV3 {

	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임
	private final OrderRepositoryV3 orderRepositoryV0;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderService.request()");
			orderRepositoryV0.save(itemId);
			trace.end(status);
		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}

}
