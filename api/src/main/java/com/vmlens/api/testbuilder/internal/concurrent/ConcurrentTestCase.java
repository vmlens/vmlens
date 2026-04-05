package com.vmlens.api.testbuilder.internal.concurrent;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.api.testbuilder.internal.runner.CallConcurrentCalls;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class ConcurrentTestCase<CLASS_UNDER_TEST> {

    private final Supplier<CLASS_UNDER_TEST> createClassUnderTest;
    private final List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList;
    private final List<CheckAfterJoin<CLASS_UNDER_TEST>> checkAfterJoinList;

    public ConcurrentTestCase(Supplier<CLASS_UNDER_TEST> createClassUnderTest,
                              List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList,
                              List<CheckAfterJoin<CLASS_UNDER_TEST>> checkAfterJoinList) {
        this.createClassUnderTest = createClassUnderTest;
        this.concurrentCallList = concurrentCallList;
        this.checkAfterJoinList = checkAfterJoinList;
    }

    public String getLabel() {
        StringBuilder builder = new StringBuilder();
        for(ConcurrentCall<CLASS_UNDER_TEST> concurrentCall : concurrentCallList ) {
            builder.append(concurrentCall.callKey().getLabel());
        }
        return builder.toString();
    }

    public List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList() {
        return concurrentCallList;
    }

    public Supplier<CLASS_UNDER_TEST> createClassUnderTest() {
        return createClassUnderTest;
    }

    public List<CheckAfterJoin<CLASS_UNDER_TEST>> checkAfterJoinList() {
        return checkAfterJoinList;
    }
}
