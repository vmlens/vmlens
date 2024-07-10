package com.vmlens.trace.agent.bootstrap.event.impl;


import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;

public interface RuntimeEvent extends SerializableEvent {


    void setThreadIndex(int threadIndex);

    void setMethodCounter(int methodCounter);

    void setLoopId(int loopId);

    void setRunId(int runId);

    void setRunPosition(int runPosition);

    void accept(RuntimeEventVisitor visitor);

}
