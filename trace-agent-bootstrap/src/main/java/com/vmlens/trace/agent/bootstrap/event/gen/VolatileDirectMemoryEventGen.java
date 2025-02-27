package com.vmlens.trace.agent.bootstrap.event.gen;

import com.vmlens.trace.agent.bootstrap.event.LoopIdAndRunId;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

import java.nio.ByteBuffer;


public class VolatileDirectMemoryEventGen {

    protected int threadIndex;
    protected int methodCounter;
    protected long objectHashCode;
    protected int operation;
    protected int loopId;
    protected int runId;
    protected int runPosition;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileDirectMemoryEventGen that = (VolatileDirectMemoryEventGen) o;
        if (threadIndex != that.threadIndex) return false;
        if (methodCounter != that.methodCounter) return false;
        if (objectHashCode != that.objectHashCode) return false;
        if (operation != that.operation) return false;
        if (loopId != that.loopId) return false;
        if (runId != that.runId) return false;
        if (runPosition != that.runPosition) return false;
        return true;
    }

    @Override
    public String toString() {
        return "VolatileDirectMemoryEventGen{" +
                "threadIndex=" + threadIndex +
                "methodCounter=" + methodCounter +
                "objectHashCode=" + objectHashCode +
                "operation=" + operation +
                "loopId=" + loopId +
                "runId=" + runId +
                "runPosition=" + runPosition +
                '}';
    }


    public void serialize(StreamRepository streamRepository) throws Exception {
        serialize(streamRepository.directMemory.
                getByteBuffer(new LoopIdAndRunId(loopId, runId), 33, EventConstants.MAX_ARRAY_SIZE * 1000));

    }

    public void serialize(ByteBuffer buffer) throws Exception {
        buffer.put((byte) 9);
        buffer.putInt(threadIndex);
        buffer.putInt(methodCounter);
        buffer.putLong(objectHashCode);
        buffer.putInt(operation);
        buffer.putInt(loopId);
        buffer.putInt(runId);
        buffer.putInt(runPosition);
    }


}