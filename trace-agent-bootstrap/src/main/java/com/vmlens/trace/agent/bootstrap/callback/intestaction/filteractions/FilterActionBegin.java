package com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class FilterActionBegin implements FilterActionsInsideMethodStrategy {
    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        int i = threadLocalDataWhenInTest.inAtomicCount();
        i++;
        threadLocalDataWhenInTest.setInAtomicCount(i);
        return i == 1;
    }
}
