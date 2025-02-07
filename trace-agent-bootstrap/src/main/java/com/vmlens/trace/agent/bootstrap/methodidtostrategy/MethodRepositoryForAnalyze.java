package com.vmlens.trace.agent.bootstrap.methodidtostrategy;

public interface MethodRepositoryForAnalyze extends MethodCallIdMap {

    int getIdAndSetMethodIsSynchronized(MethodCallId methodCallId);

}
