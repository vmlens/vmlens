package com.anarsoft.trace.agent.runtime.write;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

public class PoisonedEvent implements SerializableEvent {
    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        throw new RuntimeException("should not be called");
    }
}
