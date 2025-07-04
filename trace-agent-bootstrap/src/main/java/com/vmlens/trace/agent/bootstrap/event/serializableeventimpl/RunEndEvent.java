package com.vmlens.trace.agent.bootstrap.event.serializableeventimpl;

import com.vmlens.trace.agent.bootstrap.event.gen.RunEndEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ControlEvent;

public class RunEndEvent extends RunEndEventGen implements ControlEvent {

    public RunEndEvent(int loopId, int runId) {
        this.loopId = loopId;
        this.runId = runId;
    }

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }

}
