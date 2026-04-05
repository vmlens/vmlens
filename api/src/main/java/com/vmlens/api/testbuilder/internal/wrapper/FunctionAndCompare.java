package com.vmlens.api.testbuilder.internal.wrapper;

import com.vmlens.api.testbuilder.internal.callkey.CallKey;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnlyFactory;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.vmlens.api.testbuilder.internal.wrapper.CreateLabel.createLabel;

public class FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> implements RecordReadOnlyFactory<CLASS_UNDER_TEST> {

    private final Function<CLASS_UNDER_TEST,READ_VALUE> function;
    private final BiPredicate<READ_VALUE,READ_VALUE> compare;
    private final CallKey callKey;
    private final int automaticTestId;
    private final int automaticTestMethodId;

    public FunctionAndCompare(Function<CLASS_UNDER_TEST, READ_VALUE> function,
                              BiPredicate<READ_VALUE, READ_VALUE> compare,
                              CallKey callKey,
                              int automaticTestId,
                              int automaticTestMethodId) {
        this.function = function;
        this.compare = compare;
        this.callKey = callKey;
        this.automaticTestId = automaticTestId;
        this.automaticTestMethodId = automaticTestMethodId;
    }

    @Override
    public RecordUpdate<CLASS_UNDER_TEST> create() {
        return new RecordUpdateForFunctionWrapper<>(this);
    }

    public READ_VALUE apply(CLASS_UNDER_TEST classUnderTest) {
        return function.apply(classUnderTest);
    }

    @Override
    public RecordReadOnly<CLASS_UNDER_TEST> createForAfterJoin() {
        return new RecordUpdateForFunctionWrapper<>(this);
    }

    public int automaticTestId() {
        return automaticTestId;
    }

    public int automaticTestMethodId() {
        return automaticTestMethodId;
    }

    public BiPredicate<READ_VALUE, READ_VALUE> getCompare() {
        return compare;
    }

    public CallKey callKey() {
        return callKey;
    }
}
