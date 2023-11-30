package com.woogie.tech.core2.section3.version4;

import org.springframework.stereotype.Repository;

import com.woogie.tech.core2.section3.template.AbstractTemplate;
import com.woogie.tech.core2.trace.logtrace.LogTrace;

import lombok.RequiredArgsConstructor;

// 상품이 저장하는데 1초 정도 걸린다
@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

	//private final HelloTraceV2 trace;
	//이제 이건 사용하지않고 스프링 빈으로 등록된것을 사용할것임

	private final LogTrace trace;
	public void save(String itemId) {

		AbstractTemplate<Void> template = new AbstractTemplate<>(trace) {
			@Override
			protected Void call() {

				if (itemId.equals("ex")) {
					throw new IllegalStateException("예외 발생");
				}

				sleep(1000);
				return null;
			}
		};
		template.execute("OrderRepository.save");

	}
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}