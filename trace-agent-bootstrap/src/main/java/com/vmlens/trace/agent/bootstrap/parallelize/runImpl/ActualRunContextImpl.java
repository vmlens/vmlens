package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;


import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ActualRunContextImpl implements ActualRunContext {
    private final RunContext runContext;
    private final TestThreadState testThreadState;

    public ActualRunContextImpl(RunContext runContext, TestThreadState testThreadState) {
        this.runContext = runContext;
        this.testThreadState = testThreadState;
    }

    @Override
    public void setRunIdsInRuntimeEvent(RuntimeEvent event) {
        runContext.setRunIdsInRuntimeEvent(event);
    }

    @Override
    public SendEventContext sendEventContext() {
        return testThreadState.threadLocalWrapper();
    }
}
