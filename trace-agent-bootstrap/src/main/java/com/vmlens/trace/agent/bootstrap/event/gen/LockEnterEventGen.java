package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class LockEnterEventGen {

    protected int threadIndex;
    protected int order;
    protected int monitorId;
    protected int methodCounter;
    protected boolean isShared;
    protected int lockTyp;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LockEnterEventGen that = (LockEnterEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (order != that.order) return false;
        if (monitorId != that.monitorId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (isShared != that.isShared) return false;
        if (lockTyp != that.lockTyp) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LockEnterEventGen{" +
                "threadIndex=" + threadIndex +
                "order=" + order +
                "monitorId=" + monitorId +
                "methodCounter=" + methodCounter +
                "isShared=" + isShared +
                "lockTyp=" + lockTyp +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.syncActions.
                getByteBuffer(34, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 10);
        buffer.putInt(threadIndex);
        buffer.putInt(order);
        buffer.putInt(monitorId);
        buffer.putInt(methodCounter);
        buffer.put((byte) (isShared ? 1 : 0));
        buffer.putInt(lockTyp);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}