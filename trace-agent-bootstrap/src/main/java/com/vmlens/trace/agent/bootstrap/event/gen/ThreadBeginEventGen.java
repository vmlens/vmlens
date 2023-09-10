package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class ThreadBeginEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected long startedThreadId;
    protected int programCounter;
    protected int methodCounter;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.syncActions.getByteBuffer(slidingWindowId, 25, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 18);
        buffer.putLong(threadId);
        ;
        buffer.putLong(startedThreadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(methodCounter);
        ;
    }
}