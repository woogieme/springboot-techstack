package com.woogie.techstack.core_2.section1.version0;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV0 {


	public void save(String itemId){
		//저장 로직
		if (itemId.equals("ex")){
			throw new IllegalStateException("예외 발생");
		}
		sleep(1000);
	}

	private void sleep(int millis){
		try{
			Thread.sleep(millis);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}

// 상품이 저장하는데 1초 정도 걸린다
// 만약에 상품아이디가 ex로 넘어가면 예외 발생해서 repository에서 저장하는것이 아닌
// 예외로 처리되어 저장이 되지않고 에러가 터진다