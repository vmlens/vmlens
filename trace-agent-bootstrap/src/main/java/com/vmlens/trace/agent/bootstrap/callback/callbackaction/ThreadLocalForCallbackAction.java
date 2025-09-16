package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;

public class ThreadLocalForCallbackAction {


    protected final ThreadForParallelize threadForParallelize;

    private Integer doNotTraceSetAtStackTraceCount;
    private int insideVMLens;

    public ThreadLocalForCallbackAction(ThreadForParallelize threadForParallelize) {
        this.threadForParallelize = threadForParallelize;
    }

    public void startDoNotTrace() {
        if(insideVMLens > 0) {
            return;
        }
        insideVMLens++;
        try {
            if (doNotTraceSetAtStackTraceCount == null) {
                doNotTraceSetAtStackTraceCount = threadForParallelize.getStacktraceDepth();
            }
        }
        finally{
            insideVMLens--;
        }
    }

    public void endDoNotTrace() {
        if(insideVMLens > 0) {
            return;
        }
        insideVMLens++;
        try {
            if (doNotTraceSetAtStackTraceCount != null &&
                    doNotTraceSetAtStackTraceCount == threadForParallelize.getStacktraceDepth()) {
                doNotTraceSetAtStackTraceCount = null;
            }
        }
        finally{
            insideVMLens--;
        }
    }

    /**
     * we currently do not need to check the stacktrace depth here, since all
     * do not trace classes do not let the exception escape
     */

    boolean canProcess() {
        if(insideVMLens > 0) {
            return false;
        }
        return doNotTraceSetAtStackTraceCount == null;
    }

    public void incrementInsideVMLens() {
        insideVMLens++;
    }

    public void decrementInsideVMLens() {
        insideVMLens--;
    }

}
