package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

public class AgentLoggerNoOp implements AgentLogger {
    @Override
    public void debug(Class location, String message) {
        AgentLogCallback.log(location, message);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }
}
