package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class VolatileArrayAccessEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int order;
    protected long index;
    protected int methodCounter;
    protected int methodId;
    protected int operation;
    protected long objectHashCode;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.syncActions.getByteBuffer(slidingWindowId, 57, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 8);
        buffer.putLong(threadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(order);
        ;
        buffer.putLong(index);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.putInt(methodId);
        ;
        buffer.putInt(operation);
        ;
        buffer.putLong(objectHashCode);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}