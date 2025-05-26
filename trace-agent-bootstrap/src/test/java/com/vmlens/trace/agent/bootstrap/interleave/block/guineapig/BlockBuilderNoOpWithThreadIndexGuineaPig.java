package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public class BlockBuilderNoOpWithThreadIndexGuineaPig implements InterleaveAction {

    private final int threadIndex;

    @Override
    public void addToBlockingLockRelationBuilder(Position position, BlockingLockRelationBuilder builder) {

    }

    public BlockBuilderNoOpWithThreadIndexGuineaPig(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void blockBuilderAdd(Position myPosition, ActiveLockCollection mapContainingStack, MapOfBlocks result) {

    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        return false;
    }
}
