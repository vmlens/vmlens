package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;


import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnterOperation;

public class BlockEndBuilder {

    private final BlockAndOperationBuilder blockAndOperationBuilder;
    private final LockEnterOperation blockStart;


    public BlockEndBuilder(BlockAndOperationBuilder blockAndOperationBuilder,
                           LockEnterOperation blockStart) {
        this.blockAndOperationBuilder = blockAndOperationBuilder;
        this.blockStart = blockStart;
    }

    public Block exit() {
        return new Block(blockStart, new BlockEnd(blockAndOperationBuilder.nextPosition()));
    }

}
