package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilderKey;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockKey;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveAction;

public class ThreadBegin implements InterleaveAction  {
    @Override
    public BlockBuilderKey blockBuilderKey() {
        return new ThreadStartOrBeginKey();
    }

    @Override
    public BlockKey blockKey() {
        return new ThreadStartOrBeginKey();
    }

    @Override
    public boolean startsAlternatingOrder(InterleaveAction interleaveAction) {
        return false;
    }

    @Override
    public boolean startsFixedOrder(InterleaveAction interleaveAction,int otherThreadIndex) {
        return false;
    }
}
