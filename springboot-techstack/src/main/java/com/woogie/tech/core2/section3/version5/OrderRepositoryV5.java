package com.woogie.tech.core2.section3.version5;

import org.springframework.stereotype.Repository;

import com.woogie.tech.core2.section3.callback.TraceTemplate;
import com.woogie.tech.core2.trace.logtrace.LogTrace;

// 상품이 저장하는데 1초 정도 걸린다
@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate template;

	public OrderRepositoryV5(LogTrace trace) {
		this.template = new TraceTemplate(trace);
	}

	public void save(String itemId) {

		template.execute("OrderRepository.request()", () -> {
			if (itemId.equals("ex")) {
				throw new IllegalStateException("예외 발생");
			}
			sleep(1000);
			return null;
		});

		// template.execute("OrderRepository.request()", new TraceCallback<>() {
		// 	@Override
		// 	public Object call() {
		// 		if (itemId.equals("ex")) {
		// 			throw new IllegalStateException("예외 발생");
		// 		}
		// 		sleep(1000);
		// 		return null;
		// 	}
		// });
	}
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}