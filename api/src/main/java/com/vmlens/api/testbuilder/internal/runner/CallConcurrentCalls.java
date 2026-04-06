package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;

import java.util.List;

public class CallConcurrentCalls<CLASS_UNDER_TEST> implements Runnable  {

    private final CLASS_UNDER_TEST classUnderTest;
    private final List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCalls;
    private final AllInterleavings allInterleavings;
    private boolean success;

    public CallConcurrentCalls(CLASS_UNDER_TEST classUnderTest,
                               List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCalls,
                               AllInterleavings allInterleavings) {
        this.classUnderTest = classUnderTest;
        this.concurrentCalls = concurrentCalls;
        this.allInterleavings = allInterleavings;
    }

    @Override
    public void run() {
        for(ConcurrentCall<CLASS_UNDER_TEST> concurrentCall : concurrentCalls) {
            success = success | concurrentCall.execute(classUnderTest,allInterleavings);
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getLabel() {
        StringBuilder sb = new StringBuilder();
        for(ConcurrentCall<CLASS_UNDER_TEST> concurrentCall : concurrentCalls) {
            sb.append( concurrentCall.callKey().getLabel() );
        }
        return sb.toString();
    }
}
