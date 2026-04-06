package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.callkey.CallKey;

import java.util.Objects;

public class CallKeyMock implements CallKey {

    private final String value;

    public CallKeyMock(String value) {
        this.value = value;
    }

    @Override
    public String getLabel() {
        return value;
    }

    @Override
    public int automaticTestType() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CallKeyMock that = (CallKeyMock) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
