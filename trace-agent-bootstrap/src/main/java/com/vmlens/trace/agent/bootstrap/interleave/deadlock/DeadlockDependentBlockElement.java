package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlternatingOrderElementStrategy;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public class DeadlockDependentBlockElement implements DependentBlockElement {

    private final LockKey forLock;

    public DeadlockDependentBlockElement(LockKey forLock) {
        this.forLock = forLock;
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement interleaveAction) {
        DeadlockDependentBlockElement other = (DeadlockDependentBlockElement) interleaveAction;
        return forLock.equals(other.forLock);
    }

    @Override
    public AlternatingOrderElementStrategy alternatingOrderElementStrategy() {
        throw new RuntimeException("should not be called");
    }
}
