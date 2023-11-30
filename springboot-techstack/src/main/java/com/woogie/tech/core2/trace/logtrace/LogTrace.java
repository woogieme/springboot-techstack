package com.woogie.tech.core2.trace.logtrace;

import com.woogie.tech.core2.trace.TraceStatus;

public interface LogTrace {

	TraceStatus begin(String message);

	void end(TraceStatus status);

	void exception(TraceStatus status,Exception e);
}
