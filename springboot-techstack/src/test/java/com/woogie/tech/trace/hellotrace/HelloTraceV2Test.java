package com.woogie.tech.trace.hellotrace;

import org.junit.jupiter.api.Test;

import com.woogie.tech.core2.section1.trace.TraceStatus;
import com.woogie.tech.core2.section1.trace.hellotrace.HelloTraceV2;

public class HelloTraceV2Test {

	@Test
	void begin_end(){
		HelloTraceV2 trace = new HelloTraceV2();
		TraceStatus status1 = trace.begin("hello");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
		trace.end(status1);
		trace.end(status2);
	}

	@Test
	void begin_exception(){
		HelloTraceV2 trace =new HelloTraceV2();
		TraceStatus status1 = trace.begin("hello");
		TraceStatus status2 = trace.beginSync(status1.getTraceId(),"hello2");
		trace.exception(status1,new IllegalStateException());
		trace.exception(status2,new IllegalStateException());

	}
}

//온전한 테스트 코드가 아니다
//일반적으로 테스트코드라고한다면 자동으로 검증하는 과정이 필요하다
//지금 이테스트는 검증하는과정이없고 결과를 콘솔로 직접확인해야한다
//지금은 응답값이 존재하지않고 콘솔의 결과가 출력이되는것뿐이고, 로그들이 return 되는것이 아니다.
//이런것을 검증하려면 구조를 바꾸거나 이것을 검증하기위한 테스트 코드가 있지만 복잡함 지금은 그냥 예제를 단순하게 보여주기위해서
//이렇게 하는것임 => 학습용 테스트