package com.vmlens.api.testbuilder.internal.wrapper;

import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;
import com.vmlens.api.testbuilder.internal.value.Value;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.api.testbuilder.internal.wrapper.FunctionCompareAndExpectedResult.process;

public class RecordUpdateForFunctionWrapper<CLASS_UNDER_TEST,READ_VALUE> implements RecordUpdate<CLASS_UNDER_TEST> ,
        RecordReadOnly<CLASS_UNDER_TEST> {

    private final FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> functionAndCompare;
    private final List<Value<READ_VALUE>> result = new LinkedList<>();

    public RecordUpdateForFunctionWrapper(FunctionAndCompare<CLASS_UNDER_TEST, READ_VALUE> functionAndCompare) {
        this.functionAndCompare = functionAndCompare;
    }

    @Override
    public void executeForRecording(CLASS_UNDER_TEST classUnderTest) {
        result.add(process(functionAndCompare,classUnderTest));
    }

    @Override
    public void read(CLASS_UNDER_TEST classUnderTest) {
        result.add(process(functionAndCompare,classUnderTest));
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
