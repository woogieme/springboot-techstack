package com.woogie.tech.core2.section1.trace;

public class TraceStatus {

	private TraceId traceId;
	private Long startTimeMs;
	private String message;

	public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
		this.traceId = traceId;
		this.startTimeMs = startTimeMs;
		this.message = message;
	}

	public TraceId getTraceId() {
		return traceId;
	}

	public Long getStartTimeMs() {
		return startTimeMs;
	}

	public String getMessage() {
		return message;
	}
}


//로그의 상태정보를 보여주는 클래스
//시작이 있으면 끝이 존재하는법
//즉 시작시간이 존재한다면 끝 시간조차도 존재한다.
//로그를 시작할때 갖고있는 시작정보를 알려주는 클래스
//로그를 시작할때의 상태 정보를 가지고 있고, 상태 정보는 로그를 종료할때 사용된다.
//traceId: 내부에 트랜잭션ID와 level을 가지고 있다.
//startTimeMs: 로그 시작시간이다 로그 종료시 이 시작 시간을 기준으로 시작~종료까지 전체 수행 시간을 구할수 있다.
//message: 시작시 사용한 메세지이다. 이후 로그 종료후에도 이 메세지를 사용해서 출력한다
