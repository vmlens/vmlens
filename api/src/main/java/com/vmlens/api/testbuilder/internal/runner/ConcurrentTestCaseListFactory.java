package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentTestCase;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnly;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdate;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdateFactory;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * creates a list of concurrent test cases out of the writeList and readOnlyList
 */
public class ConcurrentTestCaseListFactory<CLASS_UNDER_TEST> {

    private final Supplier<CLASS_UNDER_TEST> createClassUnderTest;
    private final List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList;
    private final List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList;
    private final int size;

    public ConcurrentTestCaseListFactory(Supplier<CLASS_UNDER_TEST> createClassUnderTest,
                                         List<RecordUpdateFactory<CLASS_UNDER_TEST>> writeList,
                                         List<RecordReadOnlyFactory<CLASS_UNDER_TEST>> readOnlyList,
                                         int size) {
        this.createClassUnderTest = createClassUnderTest;
        this.writeList = writeList;
        this.readOnlyList = readOnlyList;
        this.size = size;
    }

    public List<ConcurrentTestCase<CLASS_UNDER_TEST>> create() {
        // Create a list of size n
        // read only is added only once
        // and added to the first write list
        List<RecordUpdateFactory<CLASS_UNDER_TEST>> firstList = new LinkedList<>();
        firstList.addAll(readOnlyList);
        firstList.addAll(writeList);

        List listOfLists = new LinkedList<>();
        listOfLists.add(firstList);

        for(int i = 0 ; i < size; i++) {
            listOfLists.add(writeList);
        }

        // Now we create the cartesian product
        // so we have a set of lists. each list size-1 write and one read only operation
        List<List<RecordUpdateFactory<CLASS_UNDER_TEST>>> cartesianProduct = new CartesianProduct().create(listOfLists);

        List<ConcurrentTestCase<CLASS_UNDER_TEST>> result = new LinkedList<>();

        // each element of the set leads to one ConcurrentTestCase
        for(List<RecordUpdateFactory<CLASS_UNDER_TEST>> oneCombination : cartesianProduct) {
            // each permutation needs to be recoderd
            List<RecordUpdate<CLASS_UNDER_TEST>> writeRecorder = oneCombination.stream().map(RecordUpdateFactory::create).collect(Collectors.toList());
            Iterator<List<RecordUpdate<CLASS_UNDER_TEST>>> permutations =
                    new PermutationIterator<>(writeRecorder);
            List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin = createRecordReadOnly();
            while(permutations.hasNext()){
                List<RecordUpdate<CLASS_UNDER_TEST>> permutation = permutations.next();
                recordTest(permutation,recordAfterJoin);
            }

            List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList = new LinkedList<>();
            for(RecordUpdate<CLASS_UNDER_TEST> record : writeRecorder) {
                concurrentCallList.add(record.build());
            }
            result.add(new ConcurrentTestCase<>(createClassUnderTest,concurrentCallList,toCheckAfterJoin(recordAfterJoin)));
        }
        return result;
    }

    private void recordTest(List<RecordUpdate<CLASS_UNDER_TEST>> permutation,
                            List<RecordReadOnly<CLASS_UNDER_TEST>> recordAfterJoin) {
        CLASS_UNDER_TEST classUnderTest = createClassUnderTest.get();
        for(RecordUpdate<CLASS_UNDER_TEST> record : permutation) {
            record.executeForRecording(classUnderTest);
        }
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
