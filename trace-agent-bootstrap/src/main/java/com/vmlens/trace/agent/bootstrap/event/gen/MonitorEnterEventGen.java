package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class MonitorEnterEventGen {
    protected int slidingWindowId;
    protected long threadId;
    protected int programCounter;
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

        MonitorEnterEventGen that = (MonitorEnterEventGen) o;
        if (threadId != that.threadId) return false;
        if (programCounter != that.programCounter) return false;
        if (order != that.order) return false;
        if (monitorId != that.monitorId) return false;
        if (methodCounter != that.methodCounter) return false;
        if (methodId != that.methodId) return false;
        if (position != that.position) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "MonitorEnterEventGen{" +
                "threadId=" + threadId +
                "programCounter=" + programCounter +
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
        serialize(streamRepository.monitor.getByteBuffer(slidingWindowId, 45, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 14);
        buffer.putLong(threadId);
        buffer.putInt(programCounter);
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