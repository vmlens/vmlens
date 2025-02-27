package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.nio.ByteBuffer;


public class LockExitEventGen {

    protected int threadIndex;
    protected int order;
    protected int methodCounter;
    protected long objectHashCode;
    protected boolean isShared;
    protected int lockTyp;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockExitEventGen that = (LockExitEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (order != that.order) return false;
        if (methodCounter != that.methodCounter) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (isShared != that.isShared) return false;
        if (lockTyp != that.lockTyp) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LockExitEventGen{" +
                "threadIndex=" + threadIndex +
                "order=" + order +
                "methodCounter=" + methodCounter +
                "objectHashCode=" + objectHashCode +
                "isShared=" + isShared +
                "lockTyp=" + lockTyp +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 38, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 11);
        buffer.putInt(threadIndex);
        buffer.putInt(order);
        buffer.putInt(methodCounter);
        buffer.putLong(objectHashCode);
        buffer.put((byte) (isShared ? 1 : 0));
        buffer.putInt(lockTyp);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}