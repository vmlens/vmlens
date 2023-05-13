package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

public interface ParallelizeLoopFactory {
    ParallelizeLoop create(int loopId);
    AgentLogger agentLogger();
}
