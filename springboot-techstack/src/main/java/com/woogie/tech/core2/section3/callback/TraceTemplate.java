package com.woogie.tech.core2.section3.callback;

import com.woogie.tech.core2.trace.TraceStatus;
import com.woogie.tech.core2.trace.logtrace.LogTrace;

public class TraceTemplate {

	private final LogTrace trace;

	public TraceTemplate(LogTrace trace) {
		this.trace = trace;
	}

	//message 데이터와 콜백함수는 TraceCallback을 전달받는다. (call임)
	public <T> T execute(String message, TraceCallback<T> callback){
		TraceStatus status = null;
		try {
			status = trace.begin(message);
			//로직 호출
			//여기서 back이 이루어짐
			T result =callback.call();

			trace.end(status);
			return result;

		} catch (Exception e)

		{
			trace.exception(status, e);
			throw e;
		}

	}
}
