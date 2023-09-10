package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class LoopEndEventGen {
    protected int slidingWindowId;
    protected int loopId;
    protected int status;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.scheduler.getByteBuffer(slidingWindowId, 9, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 25);
        buffer.putInt(loopId);
        ;
        buffer.putInt(status);
        ;
    }
}