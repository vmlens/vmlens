package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

public class AgentLoggerForTest implements AgentLogger  {
    @Override
    public void debug(String message) {
        System.out.println(message);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }
}
