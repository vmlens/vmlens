package com.vmlens.api.atomic.internal.wrapper;

import com.vmlens.api.atomic.internal.recording.RecordReadOnly;
import com.vmlens.api.atomic.internal.recording.RecordUpdate;
import com.vmlens.api.atomic.internal.recording.RecordReadOnlyFactory;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static com.vmlens.api.atomic.internal.wrapper.CreateLabel.createLabel;

public class FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> implements RecordReadOnlyFactory<CLASS_UNDER_TEST> {

    private final Function<CLASS_UNDER_TEST,READ_VALUE> function;
    private final BiPredicate<READ_VALUE,READ_VALUE> compare;
    private final int addPosition;
    private final boolean isReadOnly;
    private final int automaticTestId;
    private final int automaticTestMethodId;
    private final int automaticTestType;

    public FunctionAndCompare(Function<CLASS_UNDER_TEST, READ_VALUE> function,
                              BiPredicate<READ_VALUE, READ_VALUE> compare,
                              int addPosition,
                              boolean isReadOnly,
                              int automaticTestId,
                              int automaticTestMethodId,
                              int automaticTestType) {
        this.function = function;
        this.compare = compare;
        this.addPosition = addPosition;
        this.isReadOnly = isReadOnly;
        this.automaticTestId = automaticTestId;
        this.automaticTestMethodId = automaticTestMethodId;
        this.automaticTestType = automaticTestType;
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
            return createLabel(" addReadOnly", addPosition );
        }
        return createLabel(" addUpdate", addPosition );
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

    public int automaticTestType() {
        return automaticTestType;
    }
}
