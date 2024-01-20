package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileArrayAccessEventGen that = (VolatileArrayAccessEventGen) o;
        if (threadId != that.threadId) return false;
        if (programCounter != that.programCounter) return false;
        if (order != that.order) return false;
        if (index != that.index) return false;
        if (methodCounter != that.methodCounter) return false;
        if (methodId != that.methodId) return false;
        if (operation != that.operation) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "VolatileArrayAccessEventGen{" +
                "threadId=" + threadId +
                "programCounter=" + programCounter +
                "order=" + order +
                "index=" + index +
                "methodCounter=" + methodCounter +
                "methodId=" + methodId +
                "operation=" + operation +
                "objectHashCode=" + objectHashCode +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.syncActions);
    }

    public void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception {
        serialize(streamWrapperWithSlidingWindow.getByteBuffer(slidingWindowId, 57, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 8);
        buffer.putLong(threadId);
        buffer.putInt(programCounter);
        buffer.putInt(order);
        buffer.putLong(index);
        buffer.putInt(methodCounter);
        buffer.putInt(methodId);
        buffer.putInt(operation);
        buffer.putLong(objectHashCode);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}