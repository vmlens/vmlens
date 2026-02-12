package com.vmlens.api.atomic.internal.value;

import java.util.function.BiPredicate;

public class ObjectValue<VALUE> implements Value<VALUE> {

    private final VALUE expected;

    public ObjectValue(VALUE expected) {
        this.expected = expected;
    }

    @Override
    public boolean isExceptedException(Throwable throwable) {
        return false;
    }

    @Override
    public boolean isExceptedValue(VALUE actual, BiPredicate<VALUE, VALUE> compare) {
        return compare.test(expected,actual);
    }

    @Override
    public boolean test(Value<VALUE> value, BiPredicate<VALUE, VALUE> compare) {
        return value.isExceptedValue(expected,compare);
    }

    @Override
    public boolean isExceptedNull() {
        return false;
    }
}
