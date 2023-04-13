package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilderKey;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockKey;

public class ThreadStartOrBeginKey implements BlockKey, BlockBuilderKey  {
    @Override
    public int hashCode() {
        return 131;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }
}
