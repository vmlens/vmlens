package com.vmlens.api.atomic.internal.concurrent;

import com.vmlens.api.AllInterleavings;

public class CallConcurrentCall<CLASS_UNDER_TEST> implements Runnable  {

    private final CLASS_UNDER_TEST classUnderTest;
    private final ConcurrentCall<CLASS_UNDER_TEST> concurrentCall;
    private final AllInterleavings allInterleavings;
    private boolean success;

    public CallConcurrentCall(CLASS_UNDER_TEST classUnderTest,
                              ConcurrentCall<CLASS_UNDER_TEST> concurrentCall,
                              AllInterleavings allInterleavings) {
        this.classUnderTest = classUnderTest;
        this.concurrentCall = concurrentCall;
        this.allInterleavings = allInterleavings;
    }

    @Override
    public void run() {
        success = concurrentCall.execute(classUnderTest,allInterleavings);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getLabel() {
        return concurrentCall.getLabel();
    }
}
