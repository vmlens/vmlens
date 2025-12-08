package com.vmlens.trace.agent.bootstrap.event.specialevents;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.*;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.LastInterleaveActionInThread;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

import java.io.DataOutputStream;
/*
* Fixme probably makes sense to change the inheritance relation
*  see InterleaveRun process
*
*
 */

public class LastActionInThreadRuntimeEvent implements RuntimeEvent, InterleaveActionFactory {

    private final int methodId;
    private final int threadIndex;

    public LastActionInThreadRuntimeEvent(int methodId, int threadIndex) {
        this.methodId = methodId;
        this.threadIndex = threadIndex;
    }

    @Override
    public InterleaveAction createAndSend(CreateInterleaveActionContext context,
                                          ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                                          SendEvent sendEvent,
                                          int positionInRun) {
        return new LastInterleaveActionInThread(threadIndex);
    }


    @Override
    public InterleaveActionFactory asInterleaveActionFactory() {
        return this;
    }

    @Override
    public PluginEventOnly asPluginEventOnly() {
        return null;
    }

    @Override
    public void setMethodCounter(PerThreadCounter perThreadCounter) {

    }

    @Override
    public void setThreadIndex(int threadIndex) {

    }

    @Override
    public int loopId() {
        return 0;
    }

    @Override
    public void setLoopId(int loopId) {

    }

    @Override
    public int runId() {
        return 0;
    }

    @Override
    public void setRunId(int runId) {

    }

    @Override
    public void setRunPosition(int runPosition) {

    }

    @Override
    public void serialize(DataOutputStream buffer) throws Exception {

    }

    @Override
    public StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository) {
        return null;
    }

    @Override
    public void update(ThreadCount threadCount) {

    }
}
