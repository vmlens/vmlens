package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public class DeadlockDependentBlockElement {

    private final LockKey forLock;

    public DeadlockDependentBlockElement(LockKey forLock) {
        this.forLock = forLock;
    }

}
