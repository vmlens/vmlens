package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;


public class ThreadStartEventGen {

    protected int threadIndex;
    protected int startedThreadIndex;
    protected int methodCounter;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadStartEventGen that = (ThreadStartEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (startedThreadIndex != that.startedThreadIndex) return false;
        if (methodCounter != that.methodCounter) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "ThreadStartEventGen{" +
                "threadIndex=" + threadIndex +
                "startedThreadIndex=" + startedThreadIndex +
                "methodCounter=" + methodCounter +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 25, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 18);
        buffer.putInt(threadIndex);
        buffer.putInt(startedThreadIndex);
        buffer.putInt(methodCounter);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}