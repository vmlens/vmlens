package com.vmlens.api.atomic.internal.wrapper;

import java.util.function.BiPredicate;

public class CompareWithEquals<READ_VALUE> implements BiPredicate<READ_VALUE,READ_VALUE> {
    @Override
    public boolean test(READ_VALUE readValue, READ_VALUE readValue2) {
        return readValue.equals(readValue2);
    }
}
