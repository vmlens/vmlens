package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class VolatileAccessEventStaticGen {

    protected int threadIndex;
    protected int bytecodePosition;
    protected int order;
    protected int fieldId;
    protected int methodCounter;
    protected int methodId;
    protected boolean isWrite;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileAccessEventStaticGen that = (VolatileAccessEventStaticGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (bytecodePosition != that.bytecodePosition) return false;
        if (order != that.order) return false;
        if (fieldId != that.fieldId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (methodId != that.methodId) return false;
        if (isWrite != that.isWrite) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "VolatileAccessEventStaticGen{" +
                "threadIndex=" + threadIndex +
                "bytecodePosition=" + bytecodePosition +
                "order=" + order +
                "fieldId=" + fieldId +
                "methodCounter=" + methodCounter +
                "methodId=" + methodId +
                "isWrite=" + isWrite +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 38, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 6);
        buffer.putInt(threadIndex);
        buffer.putInt(bytecodePosition);
        buffer.putInt(order);
        buffer.putInt(fieldId);
        buffer.putInt(methodCounter);
        buffer.putInt(methodId);
        buffer.put((byte) (isWrite ? 1 : 0));
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}