package com.woogie.techstack.core_2.section1.trace;

import java.util.UUID;

public class TraceId {

	private String id;
	private int level;

	//traceId의 초기값 -> 처음 만들어질때
	public TraceId() {
		this.id = createId();
		this.level = 0;
	}


	//이 클랜스 내부에서만 사용할거기때문에 private 선언
	private TraceId(String id, int level) {
		this.id = id;
		this.level = level;
	}

	//toString은 String으로 만든다는 의미
	private String createId() {
		//abcdefgi-3cdee-sd3edf-f4fgfdsf-dfe
		//=> abcdefgi로 만들겠다는 의미
		return UUID.randomUUID().toString().substring(0,8);
	}

	public TraceId createNextId(){
		return new TraceId(id, level+1);
	}

	private TraceId createPrevId(){
		return new TraceId(id, level-1);
	}

	private boolean isFirstLevel() {
		return level==0;
	}

	public String getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}
}

//존재이유
// trace의 id는 트랜잭션 id로 생각해야하고
// level은 계층을 의미한다
