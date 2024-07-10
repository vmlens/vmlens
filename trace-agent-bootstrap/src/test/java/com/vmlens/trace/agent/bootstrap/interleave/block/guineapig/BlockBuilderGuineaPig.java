package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.InDependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;

public class BlockBuilderGuineaPig implements BlockBuilder {

    private final DependentBlock dependentBlock;
    private final Object dependentBlockKey;
    private final InDependentBlock inDependentBlock;

    public BlockBuilderGuineaPig(DependentBlock dependentBlock, Object dependentBlockKey) {
        this.inDependentBlock = null;
        this.dependentBlock = dependentBlock;
        this.dependentBlockKey = dependentBlockKey;
    }

    @Override
    public Object blockBuilderKey() {
        return null;
    }

    @Override
    public void blockBuilderAdd(Position myPosition, MapOfBlocks result) {

    }
}
