package com.vmlens.nottraced.agent.write;

import com.vmlens.trace.agent.bootstrap.event.LatestWrittenLoopAndRunId;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

public class PoisonedEvent implements SerializableEvent {
    @Override
    public void serialize(StreamRepository streamRepository,
                          LatestWrittenLoopAndRunId latestWrittenLoopAndRunId) throws Exception {
        throw new RuntimeException("should not be called");
    }
}
