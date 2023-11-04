package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MethodAtomicEnterEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodId;
    protected int methodCounter;
    protected byte hasCallback;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodAtomicEnterEventGen that = (MethodAtomicEnterEventGen) o;
        if (threadId != that.threadId) return false;
        if (methodId != that.methodId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (hasCallback != that.hasCallback) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "MethodAtomicEnterEventGen{" +
                "threadId=" + threadId +
                "methodId=" + methodId +
                "methodCounter=" + methodCounter +
                "hasCallback=" + hasCallback +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.getByteBuffer(slidingWindowId, 30, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 20);
        buffer.putLong(threadId);
        buffer.putInt(methodId);
        buffer.putInt(methodCounter);
        buffer.put(hasCallback);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}