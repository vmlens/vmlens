package com.vmlens.api.atomic.internal.value;

import java.util.function.BiPredicate;

public class ExceptionValue<VALUE> implements Value<VALUE> {

    private final Throwable exception;

    public ExceptionValue(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public boolean test(Value<VALUE> value, BiPredicate<VALUE, VALUE> compare) {
        return value.isExceptedException(exception);
    }

    @Override
    public boolean isExceptedException(Throwable throwable) {
        return throwable.getClass().equals(this.exception.getClass());
    }

    @Override
    public boolean isExceptedValue(VALUE value, BiPredicate<VALUE, VALUE> compare) {
        return false;
    }

    @Override
    public boolean isExceptedNull() {
        return false;
    }
}
