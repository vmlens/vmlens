package com.vmlens.trace.agent.bootstrap;

import java.lang.instrument.Instrumentation;

public interface AgentRuntime {
	void run(String args, Instrumentation inst);
}
