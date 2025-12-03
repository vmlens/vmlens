package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public interface CallbackActionProcessor {

    void vmlensApiClose(Object obj);
    boolean vmlensApiHasNext(Object obj);
    void initialize(InitializationAction initializationAction);
    boolean process(CallbackAction callbackAction);
    void startDoNotTrace();
    void endDoNotTrace();

}
