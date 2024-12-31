package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

import java.nio.ByteBuffer;

public interface RuntimeEvent extends SerializableEvent {
    void setThreadIndex(int threadIndex);

    void setMethodCounter(PerThreadCounter perThreadCounter);
    void setLoopId(int loopId);
    void setRunId(int runId);
    void setRunPosition(int runPosition);
    void serialize(ByteBuffer buffer) throws Exception;

    RuntimeEvent after(ActualRun actualRun);

    boolean isInterleaveActionFactory();
}
