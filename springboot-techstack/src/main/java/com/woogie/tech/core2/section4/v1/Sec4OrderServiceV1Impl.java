package com.woogie.tech.core2.section4.v1;

public class Sec4OrderServiceV1Impl implements Sec4OrderServiceV1 {

	private final Sec4OrderRepositoryV1 sec4OrderRepositoryV1;

	public Sec4OrderServiceV1Impl(Sec4OrderRepositoryV1 sec4OrderRepositoryV1) {
		this.sec4OrderRepositoryV1 = sec4OrderRepositoryV1;
	}

	@Override
	public void orderItem(String itemId) {
		sec4OrderRepositoryV1.save(itemId);
	}
}
