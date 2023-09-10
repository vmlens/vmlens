package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodCallbackEnterEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.scheduler.getByteBuffer(slidingWindowId, 25, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 22);
        buffer.putLong(threadId);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}