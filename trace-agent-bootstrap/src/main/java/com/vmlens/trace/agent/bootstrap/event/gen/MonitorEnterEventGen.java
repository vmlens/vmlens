package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class MonitorEnterEventGen {

    protected int threadIndex;
    protected int order;
    protected int monitorId;
    protected int methodCounter;
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
        if (monitorId != that.monitorId) return false;
        if (methodCounter != that.methodCounter) return false;
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
                "monitorId=" + monitorId +
                "methodCounter=" + methodCounter +
                "methodId=" + methodId +
                "bytecodePosition=" + bytecodePosition +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 37, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 14);
        buffer.putInt(threadIndex);
        buffer.putInt(order);
        buffer.putInt(monitorId);
        buffer.putInt(methodCounter);
        buffer.putInt(methodId);
        buffer.putInt(bytecodePosition);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}