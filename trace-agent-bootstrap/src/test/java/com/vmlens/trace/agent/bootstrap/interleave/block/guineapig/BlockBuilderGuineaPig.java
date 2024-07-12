package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;

public class BlockBuilderGuineaPig implements BlockBuilder {

    private final DependentBlock dependentBlock;
    private final Object dependentBlockKey;
    private final ElementAndPosition<InDependentBlock> inDependentBlock;

    public BlockBuilderGuineaPig(DependentBlock dependentBlock, Object dependentBlockKey) {
        this.inDependentBlock = null;
        this.dependentBlock = dependentBlock;
        this.dependentBlockKey = dependentBlockKey;
    }

    public BlockBuilderGuineaPig(ElementAndPosition<InDependentBlock> inDependentBlock) {
        this.inDependentBlock = inDependentBlock;
        this.dependentBlock = null;
        this.dependentBlockKey = null;
    }

    @Override
    public void blockBuilderAdd(Position myPosition, MapContainingStack mapContainingStack, MapOfBlocks result) {
        if (dependentBlock != null) {
            result.dependentBlocks().put(dependentBlockKey, dependentBlock);
        } else {
            result.addInDependent(inDependentBlock);
        }
    }
}
