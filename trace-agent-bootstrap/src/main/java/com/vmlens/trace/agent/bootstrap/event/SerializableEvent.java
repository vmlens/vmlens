package com.vmlens.trace.agent.bootstrap.event;

import java.nio.ByteBuffer;

public interface SerializableEvent {
    void serialize(StreamRepository streamRepository) throws Exception;

}
