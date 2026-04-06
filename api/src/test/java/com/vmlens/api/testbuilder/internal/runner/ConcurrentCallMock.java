package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.testbuilder.internal.callkey.CallKey;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;

public class ConcurrentCallMock implements ConcurrentCall<String> {

    private final String value;

    public ConcurrentCallMock(String value) {
        this.value = value;
    }

    @Override
    public boolean execute(String s, AllInterleavings allInterleavings) {
        return false;
    }

    @Override
    public CallKey callKey() {
        return new CallKeyMock(value);
    }
}
