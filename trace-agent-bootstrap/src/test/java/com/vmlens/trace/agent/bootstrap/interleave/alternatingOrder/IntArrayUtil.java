package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import static com.vmlens.trace.agent.bootstrap.interleave.IntArray.wrap;

public class IntArrayUtil {

    public static IntArray toIntArray(Position[] positionArray) {
        int[] threadIndices = new int[positionArray.length];
        for (int i = 0; i < positionArray.length; i++) {
            threadIndices[i] = positionArray[i].threadIndex;
        }
        return wrap(threadIndices);
    }
}
