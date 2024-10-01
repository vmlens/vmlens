package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.gen.RunEndEventGen;

public class RunEndEvent extends RunEndEventGen implements SerializableEvent {

    public RunEndEvent(int loopId, int runId) {
        this.loopId = loopId;
        this.runId = runId;
    }

    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        super.serialize(streamRepository);
        streamRepository.flush();
    }
}
