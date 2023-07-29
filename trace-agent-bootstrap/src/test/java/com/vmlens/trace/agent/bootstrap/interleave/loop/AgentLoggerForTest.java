package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;

public class AgentLoggerForTest implements AgentLogger  {
    @Override
    public void debug(Class location, String message) {
        System.out.println(location.getName() + message);
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }
}
