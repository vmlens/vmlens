package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public interface LockEnter extends DependentBlockElement {
    LockKey key();
}
