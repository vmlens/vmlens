package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileDirectMemoryEventGen that = (VolatileDirectMemoryEventGen) o;
        if (threadId != that.threadId) return false;
        if (programCounter != that.programCounter) return false;
        if (methodCounter != that.methodCounter) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (operation != that.operation) return false;
        if (order != that.order) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "VolatileDirectMemoryEventGen{" +
                "threadId=" + threadId +
                "programCounter=" + programCounter +
                "methodCounter=" + methodCounter +
                "objectHashCode=" + objectHashCode +
                "operation=" + operation +
                "order=" + order +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.directMemory.getByteBuffer(slidingWindowId, 45, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 9);
        buffer.putLong(threadId);
        buffer.putInt(programCounter);
        buffer.putInt(methodCounter);
        buffer.putLong(objectHashCode);
        buffer.putInt(operation);
        buffer.putInt(order);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}