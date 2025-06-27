package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.conditionkey.ConditionKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;

public class ConditionWait {

    private final int threadIndex;
    private final LockKey lockKey;

    public ConditionWait(int threadIndex,
                         ConditionKey conditionKey,
                         LockKey lockKey) {
        this.threadIndex = threadIndex;
        this.lockKey = lockKey;
    }
}
