package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.nio.ByteBuffer;


public class LoopStartEventGen {

    protected int loopId;
    protected int runId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopStartEventGen that = (LoopStartEventGen) o;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoopStartEventGen{" +
                "loopId=" + loopId +
                "runId=" + runId +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.control.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 9, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 24);
        buffer.putInt(loopId);
        buffer.putInt(runId);
    }


}