package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class FieldAccessEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int fieldId;
    protected int methodCounter;
    protected int operation;
    protected int methodId;
    protected boolean stackTraceIncomplete;
    protected long objectHashCode;
    protected boolean showSharedMemory;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldAccessEventGen that = (FieldAccessEventGen) o;
        if (threadId != that.threadId) return false;
        if (programCounter != that.programCounter) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (operation != that.operation) return false;
        if (methodId != that.methodId) return false;
        if (stackTraceIncomplete != that.stackTraceIncomplete) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (showSharedMemory != that.showSharedMemory) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "FieldAccessEventGen{" +
                "threadId=" + threadId +
                "programCounter=" + programCounter +
                "fieldId=" + fieldId +
                "methodCounter=" + methodCounter +
                "operation=" + operation +
                "methodId=" + methodId +
                "stackTraceIncomplete=" + stackTraceIncomplete +
                "objectHashCode=" + objectHashCode +
                "showSharedMemory=" + showSharedMemory +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.field);
    }

    public void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception {
        serialize(streamWrapperWithSlidingWindow.getByteBuffer(slidingWindowId, 51, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 2);
        buffer.putLong(threadId);
        buffer.putInt(programCounter);
        buffer.putInt(fieldId);
        buffer.putInt(methodCounter);
        buffer.putInt(operation);
        buffer.putInt(methodId);
        buffer.put((byte) (stackTraceIncomplete ? 1 : 0));
        buffer.putLong(objectHashCode);
        buffer.put((byte) (showSharedMemory ? 1 : 0));
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}