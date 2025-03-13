package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.IndependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public abstract class InterleaveActionForInDependentBlock implements InterleaveAction, IndependentBlock {

    @Override
    public void addToBlockingLockRelationBuilder(Position position, BlockingLockRelationBuilder builder) {
        // Nothing To do
    }

    @Override
    public void blockBuilderAdd(Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                MapOfBlocks result) {
        result.addInDependent(new ElementAndPosition<>(this, myPosition));
    }


}
