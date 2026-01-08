package com.vmlens.api.atomic.internal.concurrent;

public class CallConcurrentCall<CLASS_UNDER_TEST> implements Runnable  {

    private final CLASS_UNDER_TEST classUnderTest;
    private final ConcurrentCall<CLASS_UNDER_TEST> concurrentCall;
    private boolean success;

    public CallConcurrentCall(CLASS_UNDER_TEST classUnderTest,
                              ConcurrentCall<CLASS_UNDER_TEST> concurrentCall) {
        this.classUnderTest = classUnderTest;
        this.concurrentCall = concurrentCall;
    }

    @Override
    public void run() {
        success = concurrentCall.execute(classUnderTest);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getLabel() {
        return concurrentCall.getLabel();
    }
}
