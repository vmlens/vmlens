package com.vmlens.agent;

import java.lang.instrument.Instrumentation;

public interface AgentRuntime {
	void run(String args, Instrumentation inst);
}
