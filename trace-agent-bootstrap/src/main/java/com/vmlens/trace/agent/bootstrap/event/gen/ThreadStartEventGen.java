package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class ThreadStartEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected long startedThreadId;
    protected int programCounter;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadStartEventGen that = (ThreadStartEventGen) o;
        if (threadId != that.threadId) return false;
        if (startedThreadId != that.startedThreadId) return false;
        if (programCounter != that.programCounter) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "ThreadStartEventGen{" +
                "threadId=" + threadId +
                "startedThreadId=" + startedThreadId +
                "programCounter=" + programCounter +
                "methodCounter=" + methodCounter +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.syncActions.getByteBuffer(slidingWindowId, 37, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 18);
        buffer.putLong(threadId);
        buffer.putLong(startedThreadId);
        buffer.putInt(programCounter);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}