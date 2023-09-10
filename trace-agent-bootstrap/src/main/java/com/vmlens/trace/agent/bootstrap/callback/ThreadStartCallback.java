package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class ThreadStartCallback {

    public static void threadStart(Object newThread) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.beforeThreadStart(callbackStatePerThread, new RunnableOrThreadWrapper(newThread));
    }


}
