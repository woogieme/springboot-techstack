package com.woogie.techstack.core_2.section1.version0;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV0 {

	private final OrderServiceV0 orderServiceV0;

	@GetMapping("/v0/request")
	public String request(String itemId){
		orderServiceV0.orderItem(itemId);
		return "ok";
	}
}

// REST 컨트롤러는 는 컨트롤러가 있으니까 이 컨트롤러안에 컴포넌트가 있다
// 컴포넌트 스캔에 대상이 돼서 스프링빈에 자동등록이된다.