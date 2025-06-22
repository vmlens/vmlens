package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

// Fixme delete
public abstract class InterleaveActionForDependentBlock implements InterleaveAction, DependentBlockElement {

    @Override
    public void blockBuilderAdd(Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                MapOfBlocks result) {
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<>(this, myPosition),
                new ElementAndPosition<>(this, myPosition));
        result.addDependent(blockKey(), dependentBlock);
    }


    protected abstract Object blockKey();


}
