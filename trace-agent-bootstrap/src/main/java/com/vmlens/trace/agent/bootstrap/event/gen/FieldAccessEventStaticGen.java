package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class FieldAccessEventStaticGen {

    protected int threadIndex;
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
        if (threadIndex != that.threadIndex) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (operation != that.operation) return false;
        if (methodId != that.methodId) return false;
        if (stackTraceIncomplete != that.stackTraceIncomplete) return false;
        if (showSharedMemory != that.showSharedMemory) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "FieldAccessEventStaticGen{" +
                "threadIndex=" + threadIndex +
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
        serialize(streamRepository.field.
                getByteBuffer(35, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 4);
        buffer.putInt(threadIndex);
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