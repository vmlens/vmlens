package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lockcontainer.BlockStartOperation;

public interface LockEnterOrTryLock extends BlockStartOperation {
    LockKey key();
}
