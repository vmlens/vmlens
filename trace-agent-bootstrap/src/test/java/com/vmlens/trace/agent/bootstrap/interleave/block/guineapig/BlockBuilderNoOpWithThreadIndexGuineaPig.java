package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderWithThreadIndex;

public class BlockBuilderNoOpWithThreadIndexGuineaPig implements BlockBuilderWithThreadIndex {

    private final int threadIndex;

    public BlockBuilderNoOpWithThreadIndexGuineaPig(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public Object blockBuilderKey() {
        return null;
    }

    @Override
    public void blockBuilderAdd(Position myPosition, MapOfBlocks result) {

    }
}
