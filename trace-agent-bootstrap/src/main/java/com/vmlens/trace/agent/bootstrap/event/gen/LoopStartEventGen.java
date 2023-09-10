package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class LoopStartEventGen {
    protected int slidingWindowId;
    protected int loopId;

    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.scheduler.getByteBuffer(slidingWindowId, 5, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 24);
        buffer.putInt(loopId);
        ;
    }
}