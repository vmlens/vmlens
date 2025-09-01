package com.vmlens.trace.agent.bootstrap.callback.impl;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.startProcess;
import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelizeSingleton.stopProcess;

public class DoNotTraceCallbackImpl {

    public  void methodEnter(Object object, int methodId) {
        startProcess();
    }

    public  void methodExit(Object object, int methodId) {
        stopProcess();
    }
    
}
