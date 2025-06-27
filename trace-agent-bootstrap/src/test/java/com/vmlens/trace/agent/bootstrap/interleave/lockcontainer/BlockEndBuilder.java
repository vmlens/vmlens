package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;


import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.LockExit;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;

public class BlockEndBuilder {

    private final BlockAndOperationBuilder blockAndOperationBuilder;
    private final BlockStart blockStart;


    public BlockEndBuilder(BlockAndOperationBuilder blockAndOperationBuilder,
                           BlockStart blockStart) {
        this.blockAndOperationBuilder = blockAndOperationBuilder;
        this.blockStart = blockStart;
    }

    public Block exit() {
        return new Block(blockStart, new BlockEnd(blockAndOperationBuilder.nextPosition(),
                new LockExit(blockAndOperationBuilder.threadIndex(),new Lock(blockAndOperationBuilder.lockKey()))));
    }

}
