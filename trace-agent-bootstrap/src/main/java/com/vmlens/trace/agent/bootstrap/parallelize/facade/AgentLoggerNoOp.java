package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

public class AgentLoggerNoOp implements AgentLogger {
    @Override
    public void debug(String message) {

    }
    @Override
    public boolean isDebugEnabled() {
        return false;
    }
}
