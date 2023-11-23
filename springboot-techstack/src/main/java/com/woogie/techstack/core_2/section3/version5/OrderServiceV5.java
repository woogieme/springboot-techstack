package com.woogie.techstack.core_2.section3.version5;

import org.springframework.stereotype.Service;

import com.woogie.techstack.core_2.section3.callback.TraceCallback;
import com.woogie.techstack.core_2.section3.callback.TraceTemplate;
import com.woogie.techstack.core_2.section3.template.AbstractTemplate;
import com.woogie.techstack.core_2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Service
public class OrderServiceV5 {

	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임
	private final OrderRepositoryV5 orderRepositoryV0;
	private final TraceTemplate template;

	public OrderServiceV5(OrderRepositoryV5 orderRepositoryV0, LogTrace trace) {
		this.orderRepositoryV0 = orderRepositoryV0;
		this.template = new TraceTemplate(trace);
	}

	public void orderItem(String itemId) {
		//익명 내부 클래스
		// template.execute("OrderService.orderItem()", new TraceCallback<>() {
		// 	@Override
		// 	public Object call(){
		// 		orderRepositoryV0.save(itemId);
		// 		return null;
		// 	}
		// });

		//익명 내부 클래스를 람다로 전환
		template.execute("OrderService.orderItem()", () -> {
			orderRepositoryV0.save(itemId);
			return null;
		});
	}

}
