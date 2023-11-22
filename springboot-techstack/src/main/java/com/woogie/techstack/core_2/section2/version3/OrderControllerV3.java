package com.woogie.techstack.core_2.section2.version3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woogie.techstack.core_2.trace.TraceStatus;
import com.woogie.techstack.core_2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV3 {

	private final OrderServiceV3 orderServiceV0;

	private final LogTrace trace;
	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임

	@GetMapping("/v3/request")
	public String request(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderController.request()");
			orderServiceV0.orderItem(itemId);
			trace.end(status);
			return "ok";

		} catch (Exception e) {
			trace.exception(status, e);
			throw e;
		}
	}
}

// REST 컨트롤러는 는 컨트롤러가 있으니까 이 컨트롤러안에 컴포넌트가 있다
// 컴포넌트 스캔에 대상이 돼서 스프링빈에 자동등록이된다.