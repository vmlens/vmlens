package com.anarsoft.trace.agent.runtime.classanalyzer;

import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodRepositoryForAnalyze;

public class OnMethodAccess {

    private final MethodRepositoryForAnalyze methodRepositoryForAnalyze;
    private final MethodCallId methodCallId;
    private int id;

    public OnMethodAccess(MethodRepositoryForAnalyze methodRepositoryForAnalyze,
                          MethodCallId methodCallId) {
        this.methodRepositoryForAnalyze = methodRepositoryForAnalyze;
        this.methodCallId = methodCallId;
    }

    public void onSynchronized() {
        id = methodRepositoryForAnalyze.getIdAndSetMethodIsSynchronized(methodCallId);
    }

    public void onNotSynchronized() {
        id = methodRepositoryForAnalyze.asInt(methodCallId);
    }

    public int id() {
        return id;
    }
}
