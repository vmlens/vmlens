package com.vmlens.nottraced.agent.write;

import com.vmlens.transformed.agent.bootstrap.event.SerializableEvent;
import com.vmlens.transformed.agent.bootstrap.event.stream.StreamRepository;

public class PoisonedEvent implements SerializableEvent {
    @Override
    public void serialize(StreamRepository streamRepository) throws Exception {
        throw new RuntimeException("should not be called");
    }
}
