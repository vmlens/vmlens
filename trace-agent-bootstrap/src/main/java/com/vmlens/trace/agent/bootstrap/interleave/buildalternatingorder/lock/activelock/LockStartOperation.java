package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;



public interface LockStartOperation extends WithThreadIndex {

    LockKey key();
    Position position();
    boolean isRead();
    boolean canBeDeadlockParent();
    boolean canBeDeadlockChild();

}
