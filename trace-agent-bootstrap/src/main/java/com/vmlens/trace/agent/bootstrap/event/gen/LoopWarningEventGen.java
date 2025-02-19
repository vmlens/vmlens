package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;
import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;


public class LoopWarningEventGen {

    protected int loopId;
    protected int runId;
    protected int messageId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopWarningEventGen that = (LoopWarningEventGen) o;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (messageId != that.messageId) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoopWarningEventGen{" +
                "loopId=" + loopId +
                "runId=" + runId +
                "messageId=" + messageId +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.control.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 13, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 28);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(messageId);
    }


}