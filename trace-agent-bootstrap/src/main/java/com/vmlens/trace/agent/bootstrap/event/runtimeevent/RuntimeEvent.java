package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

import java.nio.ByteBuffer;

public interface RuntimeEvent extends SerializableEvent {

    void setThreadIndex(int threadIndex);
    void setMethodCounter(PerThreadCounter perThreadCounter);
    void setLoopId(int loopId);
    void setRunId(int runId);
    void setRunPosition(int runPosition);

    void setStartedThreadIndex(int startedThreadIndex);

    boolean isInterleaveActionFactory();

    void after(InterleaveRun interleaveRun,
                       CreateInterleaveActionContext context,
                       ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                       SendEvent sendEvent);

    void serialize(ByteBuffer buffer) throws Exception;

}
