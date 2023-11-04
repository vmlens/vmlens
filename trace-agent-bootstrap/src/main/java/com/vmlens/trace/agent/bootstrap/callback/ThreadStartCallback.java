package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;
public class ThreadStartCallback {

    public static void threadStart(Object newThread) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        parallelize().beforeThreadStart(callbackStatePerThread, new RunnableOrThreadWrapper(newThread));
    }


}
