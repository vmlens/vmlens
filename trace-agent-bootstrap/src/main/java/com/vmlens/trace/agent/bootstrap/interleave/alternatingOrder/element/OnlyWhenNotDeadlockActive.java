package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public class OnlyWhenNotDeadlockActive implements AlternatingOrderElementStrategy {

    private final LockKey forLock;

    public OnlyWhenNotDeadlockActive(LockKey forLock) {
        this.forLock = forLock;
    }

    @Override
    public boolean isEnabled(CreateOrderContext createOrderContext) {
        return ! createOrderContext.potentialDeadlockActivated.contains(forLock);
    }
}
