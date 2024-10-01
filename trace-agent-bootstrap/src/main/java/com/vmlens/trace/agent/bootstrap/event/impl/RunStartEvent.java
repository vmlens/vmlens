package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.RunStartEventGen;

public class RunStartEvent extends RunStartEventGen implements SerializableEvent {
    public RunStartEvent(int loopId, int runId) {
        this.loopId = loopId;
        this.runId = runId;
    }

}
