package com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class AtomicBegin implements NotInAtomicCallbackStrategy {
    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        int i = threadLocalDataWhenInTest.inAtomicCount();
        i++;
        threadLocalDataWhenInTest.setInAtomicCount(i);
        return i == 1;
    }
}
