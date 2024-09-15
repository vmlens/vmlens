package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class LoopStartEventGen {

    protected int loopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopStartEventGen that = (LoopStartEventGen) o;
        if (loopId != that.loopId) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoopStartEventGen{" +
                "loopId=" + loopId +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(5, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 24);
        buffer.putInt(loopId);
    }


}