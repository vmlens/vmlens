package com.vmlens.trace.agent.bootstrap.event;

public interface SerializableEvent {
    void serialize(StreamRepository streamRepository) throws Exception;

}
