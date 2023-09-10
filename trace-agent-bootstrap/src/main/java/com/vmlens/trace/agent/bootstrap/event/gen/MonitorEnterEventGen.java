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

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.monitor.getByteBuffer(slidingWindowId, 45, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 14);
        buffer.putLong(threadId);
        ;
        buffer.putInt(programCounter);
        ;
        buffer.putInt(order);
        ;
        buffer.putInt(monitorId);
        ;
        buffer.putInt(methodCounter);
        ;
        buffer.putInt(methodId);
        ;
        buffer.putInt(position);
        ;
        buffer.putInt(loopId);
        ;
        buffer.putInt(runId);
        ;
        buffer.putInt(runPosition);
        ;
    }
}