package com.woogie.techstack.core_2.section3.version4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woogie.techstack.core_2.section3.template.AbstractTemplate;
import com.woogie.techstack.core_2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

	private final OrderServiceV4 orderServiceV4;
	private final LogTrace trace;

	@GetMapping("/v4/request")
	public String request(String itemId) {
		AbstractTemplate<String> template = new AbstractTemplate<>(trace) {
			@Override
			protected String call() {
				orderServiceV4.orderItem(itemId);
				return "ok";
			}
		};
		return template.execute("OrderController.request()");
	}
}

// REST 컨트롤러는 는 컨트롤러가 있으니까 이 컨트롤러안에 컴포넌트가 있다
// 컴포넌트 스캔에 대상이 돼서 스프링빈에 자동등록이된다.