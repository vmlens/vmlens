package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.*;
import java.io.DataOutputStream;

public class RunStartEventGen {
    protected int slidingWindowId;
    protected int loopId;
    protected int runId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunStartEventGen that = (RunStartEventGen) o;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        return slidingWindowId == that.slidingWindowId;
    }

    @Override
    public String toString() {
        return "RunStartEventGen{" +
                "loopId=" + loopId +
                "runId=" + runId +
                '}';
    }

    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave);
    }

    public void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception {
        serialize(streamWrapperWithSlidingWindow.getByteBuffer(slidingWindowId, 9, EventConstants.MAX_ARRAY_SIZE * 1000));
    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 26);
        buffer.putInt(loopId);
        buffer.putInt(runId);
    }


}