package com.woogie.techstack.core_2.section3.version4;

import org.springframework.stereotype.Service;

import com.woogie.techstack.core_2.section3.template.AbstractTemplate;
import com.woogie.techstack.core_2.trace.TraceStatus;
import com.woogie.techstack.core_2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임
	private final OrderRepositoryV4 orderRepositoryV0;
	private final LogTrace trace;

	public void orderItem(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {
				orderRepositoryV0.save(itemId);
				return null;
			}
		};
		template.execute("OrderService.orderItem()");
	}

}
