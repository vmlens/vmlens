package com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public class WithoutAtomic implements NotInAtomicCallbackStrategy{
    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return threadLocalDataWhenInTest.inAtomicCount() == 0;
    }
}
