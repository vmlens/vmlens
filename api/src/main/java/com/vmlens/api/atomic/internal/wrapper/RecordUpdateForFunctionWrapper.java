package com.vmlens.api.atomic.internal.wrapper;

import com.vmlens.api.atomic.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.atomic.internal.concurrent.ConcurrentCall;
import com.vmlens.api.atomic.internal.recording.RecordReadOnly;
import com.vmlens.api.atomic.internal.recording.RecordUpdate;

import java.util.LinkedList;
import java.util.List;

public class RecordUpdateForFunctionWrapper<CLASS_UNDER_TEST,READ_VALUE> implements RecordUpdate<CLASS_UNDER_TEST> ,
        RecordReadOnly<CLASS_UNDER_TEST> {

    private final FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> functionAndCompare;
    private final List<READ_VALUE> result = new LinkedList<>();

    public RecordUpdateForFunctionWrapper(FunctionAndCompare<CLASS_UNDER_TEST, READ_VALUE> functionAndCompare) {
        this.functionAndCompare = functionAndCompare;
    }

    @Override
    public void executeForRecording(CLASS_UNDER_TEST classUnderTest) {
        result.add(functionAndCompare.apply(classUnderTest));
    }

    @Override
    public void read(CLASS_UNDER_TEST classUnderTest) {
        result.add(functionAndCompare.apply(classUnderTest));
    }

    @Override
    public ConcurrentCall<CLASS_UNDER_TEST> build() {
        return new FunctionCompareAndExpectedResult<>(functionAndCompare, result);
    }

    @Override
    public CheckAfterJoin<CLASS_UNDER_TEST> buildCheckAfterJoin() {
        return new FunctionCompareAndExpectedResult<>(functionAndCompare, result);
    }
}
