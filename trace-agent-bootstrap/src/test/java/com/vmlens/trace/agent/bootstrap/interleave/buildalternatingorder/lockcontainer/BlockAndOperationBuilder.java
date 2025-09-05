package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock.LockEnterOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class BlockAndOperationBuilder {

    private final int threadIndex;
    private final LockKey lockKey;
    private int positionInThread;

    public BlockAndOperationBuilder(int threadIndex, LockKey lockKey) {
        this.threadIndex = threadIndex;
        this.lockKey = lockKey;
    }

    public BlockEndBuilder enter() {
        return new BlockEndBuilder(this,
        new LockEnterOperation(pos(threadIndex,getAndIncrementPosition()),lockKey));
    }

    public int getAndIncrementPosition() {
        int temp = positionInThread;
        positionInThread++;
        return temp;
    }

    public Position nextPosition() {
        return pos(threadIndex,getAndIncrementPosition());
    }

    public int threadIndex() {
        return threadIndex;
    }

    public LockKey lockKey() {
        return lockKey;
    }
}
