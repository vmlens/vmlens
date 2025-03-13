package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.lock.Lock;

public class LockFactory {

    private final int threadIndex;
    private int position;

    public LockFactory(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public ElementAndPosition<LockEnter> enter(Lock lock) {
        ElementAndPosition<LockEnter> result = new ElementAndPosition<LockEnter>(new LockEnterImpl(threadIndex, lock),
                new Position(threadIndex, position));
        position++;
        return result;
    }

    public void exit() {
        position++;
    }
}
