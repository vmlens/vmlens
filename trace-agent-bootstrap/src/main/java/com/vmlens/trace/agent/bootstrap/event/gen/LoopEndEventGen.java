package com.vmlens.trace.agent.bootstrap.event.gen;

import java.nio.ByteBuffer;

import com.vmlens.trace.agent.bootstrap.event.*;

import java.io.DataOutputStream;

public class LoopEndEventGen {

    protected int loopId;
    protected int status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopEndEventGen that = (LoopEndEventGen) o;
        if (loopId != that.loopId) return false;
        if (status != that.status) return false;
        return true;
    }

    @Override
    public String toString() {
        return "LoopEndEventGen{" +
                "loopId=" + loopId +
                "status=" + status +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.interleave.
                getByteBuffer(9, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 25);
        buffer.putInt(loopId);
        buffer.putInt(status);
    }


}