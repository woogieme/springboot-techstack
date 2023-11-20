package com.woogie.techstack.core_2.section1.version2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woogie.techstack.core_2.section1.trace.TraceStatus;
import com.woogie.techstack.core_2.section1.trace.hellotrace.HelloTraceV2;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV2 {

	private final OrderServiceV2 orderServiceV0;
	private final HelloTraceV2 trace;

	@GetMapping("/v2/request")
	public String request(String itemId) {

		TraceStatus status = null;
		try {
			status = trace.begin("OrderController.request()");
			orderServiceV0.orderItem(status.getTraceId(), itemId);
			trace.end(status);
			return "ok";

		} catch (Exception e) {
			trace.exception(status, e);
			throw e; // 예외를 꼭 다시 던져줘야함
			//왜냐하면 trace.exception에서 멈추게된다면 로그 기능때문에 다시 못돌아올수있는
			//비지니스 로직을 건드는 행위이므로 throw e를 해줘야함
		}
	}
}

// REST 컨트롤러는 는 컨트롤러가 있으니까 이 컨트롤러안에 컴포넌트가 있다
// 컴포넌트 스캔에 대상이 돼서 스프링빈에 자동등록이된다.