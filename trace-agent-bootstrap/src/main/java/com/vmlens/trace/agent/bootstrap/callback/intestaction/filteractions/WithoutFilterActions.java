package com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class WithoutFilterActions implements FilterActionsInsideMethodStrategy {
    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return threadLocalDataWhenInTest.inAtomicCount() == 0;
    }
}
