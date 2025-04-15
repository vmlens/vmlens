package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

public class CheckIsThreadRun {

    public boolean isThreadRun() {
        for(StackTraceElement element : new Exception().getStackTrace()) {
            if(element.getClassName().startsWith("com.intellij.rt.debugger.agent")) {
                return false;
            }
        }
        return true;
    }

}
