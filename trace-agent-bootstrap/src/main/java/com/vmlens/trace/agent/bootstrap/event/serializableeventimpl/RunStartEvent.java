package com.vmlens.trace.agent.bootstrap.event.serializableeventimpl;

import com.vmlens.trace.agent.bootstrap.event.gen.RunStartEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ControlEvent;

public class RunStartEvent extends RunStartEventGen implements ControlEvent {

    public RunStartEvent(int loopId, int runId) {
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
