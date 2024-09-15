package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class MonitorExitEventGen {

    protected int threadIndex;
    protected int order;
    protected int monitorId;
    protected int methodCounter;
    protected int methodId;
    protected int position;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitorExitEventGen that = (MonitorExitEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (order != that.order) return false;
        if (monitorId != that.monitorId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (methodId != that.methodId) return false;
        if (position != that.position) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "MonitorExitEventGen{" +
                "threadIndex=" + threadIndex +
                "order=" + order +
                "monitorId=" + monitorId +
                "methodCounter=" + methodCounter +
                "methodId=" + methodId +
                "position=" + position +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.monitor.
                getByteBuffer(37, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 15);
        buffer.putInt(threadIndex);
        buffer.putInt(order);
        buffer.putInt(monitorId);
        buffer.putInt(methodCounter);
        buffer.putInt(methodId);
        buffer.putInt(position);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}