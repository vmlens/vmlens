package com.vmlens.api.automatic.internal.value;

import java.util.function.BiPredicate;

public interface Value<VALUE> {

    boolean test(Value<VALUE> value, BiPredicate<VALUE,VALUE> compare);
    boolean isExceptedException(Throwable throwable);
    boolean isExceptedValue(VALUE value, BiPredicate<VALUE,VALUE> compare);
    boolean isExceptedNull();

}
