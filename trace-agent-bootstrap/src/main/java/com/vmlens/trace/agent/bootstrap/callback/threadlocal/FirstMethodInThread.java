package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

public class FirstMethodInThread {

    private final int methodId;
    private final int stacktraceDepth;

    public FirstMethodInThread(int methodId,
                               int stacktraceDepth) {
        this.methodId = methodId;
        this.stacktraceDepth = stacktraceDepth;
    }

    public int methodId() {
        return methodId;
    }

    public int stacktraceDepth() {
        return stacktraceDepth;
    }
}
