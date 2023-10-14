package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class FieldAccessEventStaticGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
    protected int fieldId;
    protected int methodCounter;
    protected int operation;
    protected int methodId;
    protected boolean stackTraceIncomplete;
    protected boolean showSharedMemory;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldAccessEventStaticGen that = (FieldAccessEventStaticGen) o;
        if (threadId != that.threadId) return false;
        if (programCounter != that.programCounter) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (operation != that.operation) return false;
        if (methodId != that.methodId) return false;
        if (stackTraceIncomplete != that.stackTraceIncomplete) return false;
        if (showSharedMemory != that.showSharedMemory) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "FieldAccessEventStaticGen{" +
                "threadId=" + threadId +
                "programCounter=" + programCounter +
                "fieldId=" + fieldId +
                "methodCounter=" + methodCounter +
                "operation=" + operation +
                "methodId=" + methodId +
                "stackTraceIncomplete=" + stackTraceIncomplete +
                "showSharedMemory=" + showSharedMemory +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.field.getByteBuffer(slidingWindowId, 43, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 4);
        buffer.putLong(threadId);
        buffer.putInt(programCounter);
        buffer.putInt(fieldId);
        buffer.putInt(methodCounter);
        buffer.putInt(operation);
        buffer.putInt(methodId);
        buffer.put((byte) (stackTraceIncomplete ? 1 : 0));
        buffer.put((byte) (showSharedMemory ? 1 : 0));
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }
}