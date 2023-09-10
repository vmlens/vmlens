package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class ArrayAccessEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int fieldId;
    protected int methodCounter;
    protected int operation;
    protected int methodId;
    protected boolean stackTraceIncomplete;
    protected long objectHashCode;
    protected int position;
    protected int classId;
    protected boolean showSharedMemory;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.field.getByteBuffer(slidingWindowId, 59, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 5);
        buffer.putLong(threadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(fieldId);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.putInt(operation);
        ;
        buffer.putInt(methodId);
        ;
        buffer.put((byte) (stackTraceIncomplete ? 1 : 0));
        ;
        buffer.putLong(objectHashCode);
        ;
        buffer.putInt(position);
        ;
        buffer.putInt(classId);
        ;
        buffer.put((byte) (showSharedMemory ? 1 : 0));
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}