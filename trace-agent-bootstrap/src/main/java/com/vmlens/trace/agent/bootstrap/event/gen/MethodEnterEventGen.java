package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodEnterEventGen {

    protected int threadIndex;
    protected int methodId;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodEnterEventGen that = (MethodEnterEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (methodId != that.methodId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MethodEnterEventGen{" +
                "threadIndex=" + threadIndex +
                "methodId=" + methodId +
                "methodCounter=" + methodCounter +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.method.
                getByteBuffer(25, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 16);
        buffer.putInt(threadIndex);
        buffer.putInt(methodId);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}