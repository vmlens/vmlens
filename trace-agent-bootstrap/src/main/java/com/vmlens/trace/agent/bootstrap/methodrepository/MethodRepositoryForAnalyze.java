package com.vmlens.trace.agent.bootstrap.methodrepository;

public interface MethodRepositoryForAnalyze extends MethodCallIdMap {

    int getIdAndSetMethodIsSynchronized(MethodCallId methodCallId);

}
