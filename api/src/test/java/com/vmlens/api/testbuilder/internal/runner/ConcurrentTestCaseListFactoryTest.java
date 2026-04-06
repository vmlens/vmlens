package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;
import com.vmlens.api.testbuilder.internal.recording.RecordReadOnlyFactory;
import com.vmlens.api.testbuilder.internal.recording.RecordUpdateFactory;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentTestCase;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class ConcurrentTestCaseListFactoryTest {

    private Supplier<String> createClassUnderTest = new Supplier<String>() {
        @Override
        public String get() {
            return "";
        }
    };
    
    @Test
    public void onlyWriteSizeTwo() {
        // Given
        List<RecordUpdateFactory<String>> writeList = new LinkedList<>();
        writeList.add(new RecordReadOnlyFactoryMock("A"));
        writeList.add(new RecordReadOnlyFactoryMock("B"));

        ConcurrentTestCaseListFactory factory = 
                new ConcurrentTestCaseListFactory(createClassUnderTest, writeList, new LinkedList<>(), 2);
        
        // When
        List<ConcurrentTestCase<String>> result = factory.create();

        // Then
        List<String> labels = new LinkedList<>();
        for(ConcurrentTestCase<String> testCase : result) {
            StringBuilder label = new StringBuilder();
            for(ConcurrentCall<String>  call : testCase.concurrentCallList())  {
                label.append(call.callKey().getLabel());
            }
            labels.add(label.toString());
        }
        assertThat(labels,containsInAnyOrder("AAA" , "AAB" , "ABA" , "ABB" , "BAA" , "BAB" , "BBA" , "BBB"));
    }

    @Test
    public void readWriteSizeOne() {
        // Given
        List<RecordUpdateFactory<String>> writeList = new LinkedList<>();
        writeList.add(new RecordReadOnlyFactoryMock("A"));
        writeList.add(new RecordReadOnlyFactoryMock("B"));

        List<RecordReadOnlyFactory<String>> readList = new LinkedList<>();
        readList.add(new RecordReadOnlyFactoryMock("R"));

        ConcurrentTestCaseListFactory factory =
                new ConcurrentTestCaseListFactory(createClassUnderTest, writeList, readList, 1);

        // When
        List<ConcurrentTestCase<String>> result = factory.create();

        // Then
        List<String> labels = new LinkedList<>();
        for(ConcurrentTestCase<String> testCase : result) {
            StringBuilder label = new StringBuilder();
            for(ConcurrentCall<String>  call : testCase.concurrentCallList())  {
                label.append(call.callKey().getLabel());
            }
            labels.add(label.toString());
        }
        assertThat(labels,containsInAnyOrder("RA" , "RB" , "AA" , "AB" , "BA" , "BB" ));
    }

    @Test
    public void singleWrite() {
        // Given
        List<RecordUpdateFactory<String>> writeList = new LinkedList<>();
        writeList.add(new RecordReadOnlyFactoryMock("A"));

        List<RecordReadOnlyFactory<String>> readList = new LinkedList<>();

        ConcurrentTestCaseListFactory factory =
                new ConcurrentTestCaseListFactory(createClassUnderTest, writeList, readList, 1);

        // When
        List<ConcurrentTestCase<String>> result = factory.create();

        // Then
        List<String> labels = new LinkedList<>();
        for(ConcurrentTestCase<String> testCase : result) {
            StringBuilder label = new StringBuilder();
            for(ConcurrentCall<String>  call : testCase.concurrentCallList())  {
                label.append(call.callKey().getLabel());
            }
            labels.add(label.toString());
        }
        assertThat(labels,containsInAnyOrder( "AA" ));
    }

}
