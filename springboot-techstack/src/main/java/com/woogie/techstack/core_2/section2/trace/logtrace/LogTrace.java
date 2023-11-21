package com.woogie.techstack.core_2.section2.trace.logtrace;

import com.woogie.techstack.core_2.section2.trace.TraceStatus;

public interface LogTrace {

	TraceStatus begin(String message);

	void end(TraceStatus status);

	void exception(TraceStatus status,Exception e);
}
