package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class VolatileDirectMemoryEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int methodCounter;
    protected long objectHashCode;
    protected int operation;
    protected int order;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.directMemory.getByteBuffer(slidingWindowId, 45, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 9);
        buffer.putLong(threadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.putLong(objectHashCode);
        ;
        buffer.putInt(operation);
        ;
        buffer.putInt(order);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}