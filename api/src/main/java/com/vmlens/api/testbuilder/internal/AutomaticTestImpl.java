package com.vmlens.api.testbuilder.internal;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentTestCase;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdateFactory;
import com.vmlens.api.testbuilder.internal.runner.AutomaticTestRunner;
import com.vmlens.api.testbuilder.internal.runner.ConcurrentTestCaseListFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;


public class AutomaticTestImpl<CLASS_UNDER_TEST>  {

    private final Supplier<CLASS_UNDER_TEST> createClassUnderTest;
    private final List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList;
    private final List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList;
    private final int atomicTestId;
    private final int maximumIterations;
    private final int size;

    public AutomaticTestImpl(Supplier<CLASS_UNDER_TEST> createClassUnderTest,
                             List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList,
                             List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList,
                             int atomicTestId,
                             int maximumIterations, 
                             int size) {
        this.createClassUnderTest = createClassUnderTest;
        this.writeList = writeList;
        this.readOnlyList = readOnlyList;
        this.atomicTestId = atomicTestId;
        this.maximumIterations = maximumIterations;
        this.size = size;
    }

    public void runTests() {
        String name = createClassUnderTest.get().getClass().getName();
        ConcurrentTestCaseListFactory<CLASS_UNDER_TEST> concurrentTestCaseListFactory = 
                new ConcurrentTestCaseListFactory<>(createClassUnderTest,writeList,readOnlyList,size);
        List<ConcurrentTestCase<CLASS_UNDER_TEST>> testCases = concurrentTestCaseListFactory.create();
        new AutomaticTestRunner<>(testCases, size, name, maximumIterations).run();

        new AllInterleavings("not used").automaticTestSuccess(atomicTestId, name);
    }

    
}
