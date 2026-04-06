package com.vmlens.api.testbuilder.internal.wrapper;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.testbuilder.internal.callkey.CallKey;
import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.value.ExceptionValue;
import com.vmlens.api.testbuilder.internal.value.NullValue;
import com.vmlens.api.testbuilder.internal.value.ObjectValue;
import com.vmlens.api.testbuilder.internal.value.Value;

import java.util.List;

public class FunctionCompareAndExpectedResult<CLASS_UNDER_TEST,READ_VALUE> implements
        CheckAfterJoin<CLASS_UNDER_TEST>, ConcurrentCall<CLASS_UNDER_TEST>  {

    private final FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> functionAndCompare;
    private final List<Value<READ_VALUE>> expectedResult;

    public FunctionCompareAndExpectedResult(FunctionAndCompare<CLASS_UNDER_TEST, READ_VALUE> functionAndCompare,
                                            List<Value<READ_VALUE>> expectedResult) {
        this.functionAndCompare = functionAndCompare;
        this.expectedResult = expectedResult;
    }

    public static <CLASS_UNDER_TEST,READ_VALUE> Value<READ_VALUE> process(FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> function,
                                                                          CLASS_UNDER_TEST classUnderTest) {
        try {
            READ_VALUE value = function.apply(classUnderTest);
            if(value == null) {
                return new NullValue<>();
            }
            return new ObjectValue<>(value);
        }
        catch(Throwable exp) {
            return new ExceptionValue<>(exp);
        }
    }

    @Override
    public boolean readAndCheck(CLASS_UNDER_TEST classUnderTest) {
        Value<READ_VALUE> value = process(functionAndCompare,classUnderTest);
        for(Value<READ_VALUE> expected : expectedResult) {
            if(value.test(expected,functionAndCompare.getCompare())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean execute(CLASS_UNDER_TEST classUnderTest, AllInterleavings allInterleavings) {
        allInterleavings.automaticTestMethod(functionAndCompare.automaticTestId(),
                functionAndCompare.automaticTestMethodId(),
                functionAndCompare.callKey().automaticTestType());
        Value<READ_VALUE> value = process(functionAndCompare,classUnderTest);
        for(Value<READ_VALUE> expected : expectedResult) {
            if(value.test(expected,functionAndCompare.getCompare())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public CallKey callKey() {
        return functionAndCompare.callKey();
    }
}
