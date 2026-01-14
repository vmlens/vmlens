package com.vmlens.api.atomic.internal.wrapper;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.atomic.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.atomic.internal.concurrent.ConcurrentCall;

import java.util.List;

public class FunctionCompareAndExpectedResult<CLASS_UNDER_TEST,READ_VALUE> implements
        CheckAfterJoin<CLASS_UNDER_TEST>, ConcurrentCall<CLASS_UNDER_TEST>  {

    private final FunctionAndCompare<CLASS_UNDER_TEST,READ_VALUE> functionAndCompare;
    private final List<READ_VALUE> expectedResult;

    public FunctionCompareAndExpectedResult(FunctionAndCompare<CLASS_UNDER_TEST, READ_VALUE> functionAndCompare,
                                            List<READ_VALUE> expectedResult) {
        this.functionAndCompare = functionAndCompare;
        this.expectedResult = expectedResult;
    }

    @Override
    public boolean readAndCheck(CLASS_UNDER_TEST classUnderTest) {
        READ_VALUE value = functionAndCompare.apply(classUnderTest);
        for(READ_VALUE expected : expectedResult) {
            if(functionAndCompare.test(value,expected)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean execute(CLASS_UNDER_TEST classUnderTest, AllInterleavings allInterleavings) {
        allInterleavings.automaticTestMethod(functionAndCompare.automaticTestId(),
                functionAndCompare.automaticTestMethodId(),
                functionAndCompare.automaticTestType());
        READ_VALUE value = functionAndCompare.apply(classUnderTest);
        for(READ_VALUE expected : expectedResult) {
            if(functionAndCompare.test(value,expected)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getLabel() {
        return functionAndCompare.getLabel();
    }


}
