package com.woogie.techstack.trace.logtrace;

import org.junit.jupiter.api.Test;

import com.woogie.techstack.core_2.section2.trace.TraceStatus;
import com.woogie.techstack.core_2.section2.trace.logtrace.FieldLogTrace;

public class FieldLogTraceTest {

	FieldLogTrace trace = new FieldLogTrace();

	@Test
	void begin_end_level2(){
		TraceStatus status1= trace.begin("hello1");
		TraceStatus status2= trace.begin("hello2");
		TraceStatus status3= trace.begin("hello3");
		TraceStatus status4= trace.begin("hello4");

		trace.end(status4);
		trace.end(status3);
		trace.end(status2);
		trace.end(status1);

	}

	@Test
	void begin_exception_level2(){
		TraceStatus status1= trace.begin("hello1");
		TraceStatus status2= trace.begin("hello2");

		trace.exception(status2,new IllegalStateException());
		trace.exception(status1,new IllegalStateException());

	}
}
