package com.vmlens.api.atomic.internal;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.atomic.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.atomic.internal.concurrent.ConcurrentCall;
import com.vmlens.api.atomic.internal.concurrent.ConcurrentTestCase;
import com.vmlens.api.atomic.internal.recording.RecordReadOnly;
import com.vmlens.api.atomic.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.atomic.internal.recording.RecordUpdate;
import com.vmlens.api.atomic.internal.recording.RecordUpdateFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;


public class AtomicTestImpl<CLASS_UNDER_TEST>  {

    private final Supplier<CLASS_UNDER_TEST> createClassUnderTest;
    private final List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList;
    private final List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList;
    private final int atomicTestId;

    public AtomicTestImpl(Supplier<CLASS_UNDER_TEST> createClassUnderTest,
                          List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList,
                          List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList,
                          int atomicTestId) {
        this.createClassUnderTest = createClassUnderTest;
        this.writeList = writeList;
        this.readOnlyList = readOnlyList;
        this.atomicTestId = atomicTestId;
    }

    public void runTests() {
        String name = createClassUnderTest.get().getClass().getName();
        List<ConcurrentTestCase<CLASS_UNDER_TEST>> testCaseList =  createConcurrentTestCaseList();
        for(ConcurrentTestCase<CLASS_UNDER_TEST> testCase : testCaseList) {
            testCase.run(name);
        }
        new AllInterleavings("not used").automaticTestSuccess(atomicTestId, name);
    }


    private List<ConcurrentTestCase<CLASS_UNDER_TEST>> createConcurrentTestCaseList() {
        List<ConcurrentTestCase<CLASS_UNDER_TEST>> result = new LinkedList<>();

        for(RecordUpdateFactory<CLASS_UNDER_TEST> firstFactory : writeList) {
            for(RecordUpdateFactory<CLASS_UNDER_TEST> secondFactory : writeList) {
                RecordUpdate<CLASS_UNDER_TEST> first = firstFactory.create();
                RecordUpdate<CLASS_UNDER_TEST> second = secondFactory.create();
                List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin = createRecordReadOnly();
                recordTest(first,second,recordAfterJoin);
                recordTest(second,first,recordAfterJoin);
                List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList = new LinkedList<>();
                concurrentCallList.add(first.build());
                concurrentCallList.add(second.build());
                result.add(new ConcurrentTestCase<>(createClassUnderTest,concurrentCallList,toCheckAfterJoin(recordAfterJoin)));
            }
        }
        for(RecordUpdateFactory<CLASS_UNDER_TEST> firstFactory : writeList) {
            for(RecordUpdateFactory<CLASS_UNDER_TEST> secondFactory : readOnlyList) {
                RecordUpdate<CLASS_UNDER_TEST> first = firstFactory.create();
                RecordUpdate<CLASS_UNDER_TEST> second = secondFactory.create();
                List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin =  new LinkedList<>();
                recordTest(first,second,recordAfterJoin);
                recordTest(second,first,recordAfterJoin);
                List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList = new LinkedList<>();
                concurrentCallList.add(first.build());
                concurrentCallList.add(second.build());
                result.add(new ConcurrentTestCase<>(createClassUnderTest,concurrentCallList,toCheckAfterJoin(recordAfterJoin)));
            }
        }


        return result;
    }

    private void recordTest(RecordUpdate<CLASS_UNDER_TEST> first,
                            RecordUpdate<CLASS_UNDER_TEST> second,
                            List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin) {

        CLASS_UNDER_TEST classUnderTest = createClassUnderTest.get();
        first.executeForRecording(classUnderTest);
        second.executeForRecording(classUnderTest);
        for(RecordReadOnly<CLASS_UNDER_TEST> readOnly : recordAfterJoin) {
            readOnly.read(classUnderTest);
        }
    }

    private List<RecordReadOnly<CLASS_UNDER_TEST>> createRecordReadOnly() {
        List<RecordReadOnly<CLASS_UNDER_TEST>> result = new LinkedList<>();
        for(RecordReadOnlyFactory<CLASS_UNDER_TEST> readOnly : readOnlyList) {
            result.add( readOnly.createForAfterJoin() );
        }
        return result;
    }

    private static <CLASS_UNDER_TEST> List<CheckAfterJoin<CLASS_UNDER_TEST>> toCheckAfterJoin(List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin) {
        List<CheckAfterJoin<CLASS_UNDER_TEST>> result = new LinkedList<>();
        for(RecordReadOnly<CLASS_UNDER_TEST> readOnly : recordAfterJoin) {
           result.add(readOnly.buildCheckAfterJoin());
        }
        return result;

    }

}
