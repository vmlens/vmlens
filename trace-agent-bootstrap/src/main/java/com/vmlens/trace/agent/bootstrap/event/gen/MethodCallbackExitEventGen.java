package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.StreamWrapperWithSlidingWindow;

import java.nio.ByteBuffer;

public class MethodCallbackExitEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodCallbackExitEventGen that = (MethodCallbackExitEventGen) o;
        if (threadId != that.threadId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "MethodCallbackExitEventGen{" +
                "threadId=" + threadId +
                "methodCounter=" + methodCounter +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave);
    }

    public void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception {
        serialize(streamWrapperWithSlidingWindow.getByteBuffer(slidingWindowId, 25, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 23);
        buffer.putLong(threadId);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}