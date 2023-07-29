package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

// ToDo move in other package
// ToDo logger category/ type (enum)?
public interface AgentLogger {

    void debug(Class location, String message);

    boolean isDebugEnabled();

}
