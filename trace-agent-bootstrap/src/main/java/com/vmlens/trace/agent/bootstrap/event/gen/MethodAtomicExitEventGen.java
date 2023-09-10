package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodAtomicExitEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodId;
    protected int methodCounter;
    protected byte hasCallback;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.scheduler.getByteBuffer(slidingWindowId, 30, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 21);
        buffer.putLong(threadId);
        ;
        buffer.putInt(methodId);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.put(hasCallback);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}