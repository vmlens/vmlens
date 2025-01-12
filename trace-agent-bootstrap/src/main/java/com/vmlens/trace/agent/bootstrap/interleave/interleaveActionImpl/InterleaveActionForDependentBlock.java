package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapContainingStack;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public abstract class InterleaveActionForDependentBlock implements InterleaveAction, DependentBlockElement {

    @Override
    public void blockBuilderAdd(Position myPosition,
                                MapContainingStack mapContainingStack,
                                MapOfBlocks result) {
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<>(this, myPosition),
                new ElementAndPosition<>(this, myPosition));
        result.addDependent(blockKey(), dependentBlock);
    }


    protected abstract Object blockKey();


}
