package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class ThreadJoinedEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected long joinedThreadId;
    protected int programCounter;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadJoinedEventGen that = (ThreadJoinedEventGen) o;
        if (threadId != that.threadId) return false;
        if (joinedThreadId != that.joinedThreadId) return false;
        if (programCounter != that.programCounter) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "ThreadJoinedEventGen{" +
                "threadId=" + threadId +
                "joinedThreadId=" + joinedThreadId +
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
        buffer.put((byte) 19);
        buffer.putLong(threadId);
        buffer.putLong(joinedThreadId);
        buffer.putInt(programCounter);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}