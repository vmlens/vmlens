package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

// ToDo move in other package
// ToDo logger category/ type (enum)?
public interface AgentLogger {

    void debug(String message);
    boolean isDebugEnabled();

}
