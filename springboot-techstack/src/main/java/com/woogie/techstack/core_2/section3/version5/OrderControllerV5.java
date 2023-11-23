package com.woogie.techstack.core_2.section3.version5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woogie.techstack.core_2.section3.callback.TraceCallback;
import com.woogie.techstack.core_2.section3.callback.TraceTemplate;
import com.woogie.techstack.core_2.section3.template.AbstractTemplate;
import com.woogie.techstack.core_2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@RestController
public class OrderControllerV5 {

	private final OrderServiceV5 orderServiceV5;
	private final TraceTemplate template;
	//private final LogTrace trace;

	//@Autowired가 필요없는 이유 생성자가 하나라서 (싱글톤이라는소리)
	public OrderControllerV5(OrderServiceV5 orderServiceV5, LogTrace trace) {
		this.orderServiceV5 = orderServiceV5;

		//LogTrace가 의존관계 주입을 받는다. 받으면서 필요한 Trace 템플릿을 여기서 생성을한다.
		//그렇지 않다면 매번 호출할때마다 new을 생성해야하기때문에 싱글톤패턴으로 만들어두는게 낫다.
		//근데 애초에 TraceTemplate를 처음부터 스프링 빈으로 등록하고 주입받아도된다. 이부분은 선택이다.
		this.template = new TraceTemplate(trace);
	}

	@GetMapping("/v5/request")
	public String request(String itemId) {
		return template.execute("OrderController.request()", new TraceCallback<>() {
			@Override
			public String call() {
				orderServiceV5.orderItem(itemId);
				return "ok";
			}
		});
	}
}

// REST 컨트롤러는 는 컨트롤러가 있으니까 이 컨트롤러안에 컴포넌트가 있다
// 컴포넌트 스캔에 대상이 돼서 스프링빈에 자동등록이된다.