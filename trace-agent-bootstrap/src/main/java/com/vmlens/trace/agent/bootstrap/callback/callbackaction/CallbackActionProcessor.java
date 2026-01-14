package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public interface CallbackActionProcessor {

    void vmlensApiClose(Object obj);
    boolean vmlensApiHasNext(Object obj);
    void automaticTestSuccess(int id, String className);
    void automaticTestMethod(int id,  int automaticTestMethodId, int automaticTestType);

    void initialize(InitializationAction initializationAction);
    boolean process(CallbackAction callbackAction);
    void startDoNotTrace();
    void endDoNotTrace();

}
