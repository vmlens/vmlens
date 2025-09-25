package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;

public class MethodIdByteCodePositionAndThreadIndexFactory {

    public static MethodIdByteCodePositionAndThreadIndex threadIndex(int threadIndex) {
        return new MethodIdByteCodePositionAndThreadIndex(-1, -1 ,threadIndex );
    }
}
