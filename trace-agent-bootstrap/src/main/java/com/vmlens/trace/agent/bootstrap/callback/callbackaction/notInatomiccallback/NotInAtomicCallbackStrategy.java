package com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;

public interface NotInAtomicCallbackStrategy {

    boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
