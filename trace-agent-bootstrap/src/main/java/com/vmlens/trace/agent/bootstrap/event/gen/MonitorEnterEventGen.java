package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.nio.ByteBuffer;


public class MonitorEnterEventGen {

    protected int threadIndex;
    protected int order;
    protected int methodCounter;
    protected long objectHashCode;
    protected int methodId;
    protected int bytecodePosition;
    protected int loopId;
    protected int runId;
    protected int runPosition;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitorEnterEventGen that = (MonitorEnterEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (order != that.order) return false;
        if (methodCounter != that.methodCounter) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (methodId != that.methodId) return false;
        if (bytecodePosition != that.bytecodePosition) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MonitorEnterEventGen{" +
                "threadIndex=" + threadIndex +
                "order=" + order +
                "methodCounter=" + methodCounter +
                "objectHashCode=" + objectHashCode +
                "methodId=" + methodId +
                "bytecodePosition=" + bytecodePosition +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 41, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 14);
        buffer.putInt(threadIndex);
        buffer.putInt(order);
        buffer.putInt(methodCounter);
        buffer.putLong(objectHashCode);
        buffer.putInt(methodId);
        buffer.putInt(bytecodePosition);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}