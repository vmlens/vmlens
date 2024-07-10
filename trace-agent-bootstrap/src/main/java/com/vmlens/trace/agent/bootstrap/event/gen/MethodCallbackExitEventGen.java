package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodCallbackExitEventGen {

    protected int threadIndex;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodCallbackExitEventGen that = (MethodCallbackExitEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MethodCallbackExitEventGen{" +
                "threadIndex=" + threadIndex +
                "methodCounter=" + methodCounter +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(21, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 23);
        buffer.putInt(threadIndex);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}