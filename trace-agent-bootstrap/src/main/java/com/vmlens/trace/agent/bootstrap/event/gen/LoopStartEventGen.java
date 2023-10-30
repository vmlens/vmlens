package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.StreamRepository;

import java.nio.ByteBuffer;

public class LoopStartEventGen {
    protected int slidingWindowId;
    protected int loopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopStartEventGen that = (LoopStartEventGen) o;
        if (loopId != that.loopId) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "LoopStartEventGen{" +
                "loopId=" + loopId +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        ByteBuffer buffer = streamRepository.interleave.getByteBuffer(slidingWindowId, 5, EventConstants.MAX_ARRAY_SIZE * 1000);
        buffer.put((byte) 24);
        buffer.putInt(loopId);
    }
}