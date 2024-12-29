package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;


public class FieldAccessEventGen {

    protected int threadIndex;
    protected int fieldId;
    protected int methodCounter;
    protected int operation;
    protected int methodId;
    protected long objectHashCode;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldAccessEventGen that = (FieldAccessEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (operation != that.operation) return false;
        if (methodId != that.methodId) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "FieldAccessEventGen{" +
                "threadIndex=" + threadIndex +
                "fieldId=" + fieldId +
                "methodCounter=" + methodCounter +
                "operation=" + operation +
                "methodId=" + methodId +
                "objectHashCode=" + objectHashCode +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.field.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 41, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 2);
        buffer.putInt(threadIndex);
        buffer.putInt(fieldId);
        buffer.putInt(methodCounter);
        buffer.putInt(operation);
        buffer.putInt(methodId);
        buffer.putLong(objectHashCode);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}