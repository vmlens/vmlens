package com.vmlens.trace.agent.bootstrap.event;


import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitor;

import java.nio.ByteBuffer;

public interface RuntimeEvent extends SerializableEvent {


    void setThreadIndex(int threadIndex);

    void setMethodCounter(int methodCounter);

    void setLoopId(int loopId);

    void setRunId(int runId);

    void setRunPosition(int runPosition);

    void accept(RuntimeEventVisitor visitor);

    void serialize(ByteBuffer buffer) throws Exception;

}
