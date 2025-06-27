package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;


import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockStartOperation;

public class BlockEndBuilder {

    private final BlockAndOperationBuilder blockAndOperationBuilder;
    private final LockStartOperation blockStart;


    public BlockEndBuilder(BlockAndOperationBuilder blockAndOperationBuilder,
                           LockStartOperation blockStart) {
        this.blockAndOperationBuilder = blockAndOperationBuilder;
        this.blockStart = blockStart;
    }

    public Block exit() {
        return new Block(blockStart, new BlockEnd(blockAndOperationBuilder.nextPosition()));
    }

}
