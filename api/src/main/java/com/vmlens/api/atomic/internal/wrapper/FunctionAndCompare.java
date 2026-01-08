package com.vmlens.api.atomic.internal.wrapper;

import com.vmlens.api.atomic.internal.recording.RecordReadOnly;
import com.vmlens.api.atomic.internal.recording.RecordUpdate;
import com.vmlens.api.atomic.internal.recording.RecordReadOnlyFactory;

import java.util.function.BiPredicate;
import java.util.function.Function;

public class FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> implements RecordReadOnlyFactory<CLASS_UNDER_TEST> {

    private final Function<CLASS_UNDER_TEST,READ_VALUE> function;
    private final BiPredicate<READ_VALUE,READ_VALUE> compare;
    private final int addPosition;
    private final boolean isReadOnly;

    public FunctionAndCompare(Function<CLASS_UNDER_TEST, READ_VALUE> function,
                              BiPredicate<READ_VALUE, READ_VALUE> compare,
                              int addPosition,
                              boolean isReadOnly) {
        this.function = function;
        this.compare = compare;
        this.addPosition = addPosition;
        this.isReadOnly = isReadOnly;
    }

    @Override
    public RecordUpdate<CLASS_UNDER_TEST> create() {
        return new RecordUpdateForFunctionWrapper<>(this);
    }

    public READ_VALUE apply(CLASS_UNDER_TEST classUnderTest) {
        return function.apply(classUnderTest);
    }

    public boolean test(READ_VALUE readValue, READ_VALUE readValue2) {
        return compare.test(readValue, readValue2);
    }

    public String getLabel() {
        if(isReadOnly) {
            return "";
        }
        return "method call added by " + addPosition + " addUpdate";
    }


    @Override
    public RecordReadOnly<CLASS_UNDER_TEST> createForAfterJoin() {
        return new RecordUpdateForFunctionWrapper<>(this);
    }
}
