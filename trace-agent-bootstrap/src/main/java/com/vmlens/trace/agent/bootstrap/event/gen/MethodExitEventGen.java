package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class MethodExitEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int methodId;
    protected int methodCounter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MethodExitEventGen that = (MethodExitEventGen) o;
        if (threadId != that.threadId) return false;
        if (methodId != that.methodId) return false;
        if (methodCounter != that.methodCounter) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "MethodExitEventGen{" +
                "threadId=" + threadId +
                "methodId=" + methodId +
                "methodCounter=" + methodCounter +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.method.getByteBuffer(slidingWindowId, 17, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 17);
        buffer.putLong(threadId);
        buffer.putInt(methodId);
        buffer.putInt(methodCounter);
    }
}