package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodcall;

public interface AnalyzeOrTransformMethodMethodCall {

    void afterLocalLoad(int size);

    void beforeMethodCall(int callArgumentSize, int returnSize, CallType callType,
                          int methodCallId);

    // only for analyze
    void afterStackOperation(int opCode);

    void afterNew();

    // only for transform
    void afterMethodCall(int returnSize, int methodCallId);
}
