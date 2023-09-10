package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodExitEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodId;
    protected int methodCounter;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.method.getByteBuffer(slidingWindowId, 17, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 17);
        buffer.putLong(threadId);
        ;
        buffer.putInt(methodId);
        ;
        buffer.putInt(methodCounter);
        ;
    }
}