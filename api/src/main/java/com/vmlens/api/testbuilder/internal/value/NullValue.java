package com.vmlens.api.testbuilder.internal.value;

import java.util.function.BiPredicate;

public class NullValue<VALUE> implements Value<VALUE> {


    @Override
    public boolean test(Value<VALUE> value, BiPredicate<VALUE, VALUE> compare) {
        return value.isExceptedNull();
    }

    @Override
    public boolean isExceptedException(Throwable throwable) {
        return false;
    }

    @Override
    public boolean isExceptedValue(VALUE value, BiPredicate<VALUE, VALUE> compare) {
        return value == null;
    }

    @Override
    public boolean isExceptedNull() {
        return true;
    }
}
