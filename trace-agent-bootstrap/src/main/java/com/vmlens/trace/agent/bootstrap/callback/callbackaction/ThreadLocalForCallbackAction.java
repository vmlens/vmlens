package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public class ThreadLocalForCallbackAction {

    private int doNotProcessCallbackCount;

    public boolean canProcessCallback() {
        return doNotProcessCallbackCount == 0;
    }

    public void resetProcessCallbackCount() {
        doNotProcessCallbackCount = 0;
    }

    public void incrementDoNotProcessCallbackCount() {
        doNotProcessCallbackCount++;
    }

    public void decrementDoNotProcessCallbackCount() {
        doNotProcessCallbackCount--;
    }

}
