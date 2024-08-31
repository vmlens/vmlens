package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.IndependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapContainingStack;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class BlockBuilderGuineaPig implements InterleaveAction {

    private final DependentBlock dependentBlock;
    private final Object dependentBlockKey;
    private final ElementAndPosition<IndependentBlock> inDependentBlock;

    public BlockBuilderGuineaPig(DependentBlock dependentBlock, Object dependentBlockKey) {
        this.inDependentBlock = null;
        this.dependentBlock = dependentBlock;
        this.dependentBlockKey = dependentBlockKey;
    }

    public BlockBuilderGuineaPig(ElementAndPosition<IndependentBlock> inDependentBlock) {
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

    @Override
    public int threadIndex() {
        throw new RuntimeException("should not be called for this test");
    }
}
