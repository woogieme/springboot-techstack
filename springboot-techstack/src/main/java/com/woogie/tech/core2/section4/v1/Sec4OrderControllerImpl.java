package com.woogie.tech.core2.section4.v1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sec4OrderControllerImpl implements Sec4OrderControllerV1 {

	private final Sec4OrderServiceV1 sec4OrderServiceV1;

	public Sec4OrderControllerImpl(Sec4OrderServiceV1 sec4OrderServiceV1) {
		this.sec4OrderServiceV1 = sec4OrderServiceV1;
	}

	@Override
	public String request(String itemId) {
		sec4OrderServiceV1.orderItem(itemId);
		return "ok";
	}

	@Override
	public String noLog() {
		return "ok";
	}
}
