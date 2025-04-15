package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

public class CheckIsThreadRun {

    public boolean isThreadRun() {
        StackTraceElement[] stacktraces = new Exception().getStackTrace();
        if(stacktraces.length == 0) {
            return false;
        }

        /* The first method must be run

            isThreadRun:27, CheckIsThreadRun (com.vmlens.trace.agent.bootstrap.strategy.strategyall)
            methodExit:36, RunMethodStrategy (com.vmlens.trace.agent.bootstrap.strategy.strategyall)
            methodExit:56, MethodCallbackImpl (com.vmlens.trace.agent.bootstrap.callback.impl)
            methodExit:60, MethodCallback (com.vmlens.trace.agent.bootstrap.callback)
            run:27, TestReentrantReadWriteLock$1 (com.vmlens.test.maven.plugin)
        */

        int lastIndex = stacktraces.length - 1;
        if(! stacktraces[lastIndex].getMethodName().equals("run")) {
            return false;
        }

        /*
            In case of runnable we have two run methods

            isThreadRun:10, CheckIsThreadRun (com.vmlens.trace.agent.bootstrap.strategy.strategyall)
            methodEnter:23, RunMethodStrategy (com.vmlens.trace.agent.bootstrap.strategy.strategyall)
            methodEnter:47, MethodCallbackImpl (com.vmlens.trace.agent.bootstrap.callback.impl)
            methodEnter:49, MethodCallback (com.vmlens.trace.agent.bootstrap.callback)
            run:8, UpdateVolatileField (com.vmlens.test.maven.plugin)
            run:831, Thread (java.lang)
         */
        if(stacktraces.length > 1) {
            lastIndex--;
            if(! (stacktraces[lastIndex].getClassName().startsWith("com.vmlens.trace.agent.bootstrap") ||
                    stacktraces[lastIndex].getMethodName().equals("run") )) {
                return false;
            }
        }


        for( int i = lastIndex -1; i > 0; i-- ) {
            if(! stacktraces[i].getClassName().startsWith("com.vmlens.trace.agent.bootstrap")) {
                return false;
            }
        }
        return true;
    }

}
