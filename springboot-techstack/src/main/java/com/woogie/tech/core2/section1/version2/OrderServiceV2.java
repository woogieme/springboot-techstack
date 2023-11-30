package com.woogie.tech.core2.section1.version2;

import org.springframework.stereotype.Service;

import com.woogie.tech.core2.section1.trace.TraceId;
import com.woogie.tech.core2.section1.trace.TraceStatus;
import com.woogie.tech.core2.section1.trace.hellotrace.HelloTraceV2;

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
			throw e;
		}
	}

}
