package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

public interface CallbackActionProcessor {

    void vmlensApiClose(Object obj);
    boolean vmlensApiHasNext(Object obj);
    boolean process(CallbackAction callbackAction);
    void processWithCheckNewThread(CallbackAction callbackAction);
    void startDoNotTrace();
    void endDoNotTrace();

}
