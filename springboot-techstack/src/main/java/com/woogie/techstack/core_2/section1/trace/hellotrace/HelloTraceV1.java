package com.woogie.techstack.core_2.section1.trace.hellotrace;

import org.springframework.stereotype.Component;

import com.woogie.techstack.core_2.section1.trace.TraceId;
import com.woogie.techstack.core_2.section1.trace.TraceStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component //싱글톤 의뢰 => 스프링빈으로 그냥 등록할것임
public class HelloTraceV1 {

	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	private static final String EX_PREFIX = "<X-";

	//시작할떄 호출
	//로그 메시지를 파라미터로 받아 로그 출력
	//응답 결과로 현재 로그상태인 TraceStatus를 반환한다
	public TraceStatus begin(String message) {
		TraceId traceId = new TraceId();
		Long startTimeMs = System.currentTimeMillis();

		//제일 처음에 traceId가 들어가게 돼고, addSpace 로직은 화살표가 하나가 나올지 여러개가 나올지 표현이됨
		//마지막에 mesaage
		log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
			traceId.getLevel()), message);
		return new TraceStatus(traceId, startTimeMs, message);
	}


	//약간 다른점이있어서  complete라는 함수를 만들어서 핸들링함
	//실행시간 계산 + 종료시간 계산 ,정상 흐름에서만 호출
	public void end(TraceStatus status) {
		complete(status, null);
	}

	//실행시간 다다름 -> 에러 흐름에서만 호출
	public void exception(TraceStatus status, Exception e) {
		complete(status, e);
	}


	private void complete(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
		//현재시간 - 시작시간 = 끝난 시간
		long resultTimeMs = stopTimeMs - status.getStartTimeMs();
		TraceId traceId = status.getTraceId();
		if (e == null) {
			log.info("[{}] {}{} time={}ms", traceId.getId(),
				addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
				resultTimeMs);
		} else {
			log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
				addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
				e.toString());
		}
	}

	//level=0
	//level=1 |-->
	//level=2 |   |-->

	//level=2 ex|   |<X-
	//level=1 ex|<X-
	//의 역할을해주는건 addSpace

	private static String addSpace(String prefix, int level) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < level; i++) {
			sb.append( (i == level - 1) ? "|" + prefix : "| ");
		}
		return sb.toString();
	}
}
