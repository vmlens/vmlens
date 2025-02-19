package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class ArrayAccessEventGen {

    protected int threadIndex;
    protected int fieldId;
    protected int methodCounter;
    protected int operation;
    protected int methodId;
    protected long objectHashCode;
    protected int bytecodePosition;
    protected int classId;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayAccessEventGen that = (ArrayAccessEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (operation != that.operation) return false;
        if (methodId != that.methodId) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (bytecodePosition != that.bytecodePosition) return false;
        if (classId != that.classId) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ArrayAccessEventGen{" +
                "threadIndex=" + threadIndex +
                "fieldId=" + fieldId +
                "methodCounter=" + methodCounter +
                "operation=" + operation +
                "methodId=" + methodId +
                "objectHashCode=" + objectHashCode +
                "bytecodePosition=" + bytecodePosition +
                "classId=" + classId +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.nonVolatile.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 49, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 5);
        buffer.putInt(threadIndex);
        buffer.putInt(fieldId);
        buffer.putInt(methodCounter);
        buffer.putInt(operation);
        buffer.putInt(methodId);
        buffer.putLong(objectHashCode);
        buffer.putInt(bytecodePosition);
        buffer.putInt(classId);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}