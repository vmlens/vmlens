package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import java.util.Objects;

public class MethodIdByteCodePositionAndThreadIndex {

    private final int inMethodId;
    private final int byteCodePositon;
    private final int threadIndex;

    public MethodIdByteCodePositionAndThreadIndex(int inMethodId,
                                                  int byteCodePositon,
                                                  int threadIndex) {
        this.inMethodId = inMethodId;
        this.byteCodePositon = byteCodePositon;
        this.threadIndex = threadIndex;
    }

    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        MethodIdByteCodePositionAndThreadIndex that = (MethodIdByteCodePositionAndThreadIndex) object;
        return inMethodId == that.inMethodId && byteCodePositon == that.byteCodePositon && threadIndex == that.threadIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inMethodId, byteCodePositon, threadIndex);
    }
}
