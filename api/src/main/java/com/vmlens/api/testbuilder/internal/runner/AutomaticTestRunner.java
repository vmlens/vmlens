package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.api.testbuilder.internal.concurrent.CheckAfterJoin;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentTestCase;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AutomaticTestRunner<CLASS_UNDER_TEST> {

    private final List<ConcurrentTestCase<CLASS_UNDER_TEST>> concurrentTestCaseList;
    private final int size;
    private final String name;
    private final int maximumIterations;

    public AutomaticTestRunner(List<ConcurrentTestCase<CLASS_UNDER_TEST>> concurrentTestCaseList,
                               int size,
                               String name,
                               int maximumIterations) {
        this.concurrentTestCaseList = concurrentTestCaseList;
        this.size = size;
        this.name = name;
        this.maximumIterations = maximumIterations;
    }

    public void run() {
        /* First we calculate the partition of the threads according to the size
           for example for  size 2 we have to partitions: [[2, 1], [1, 1, 1]]
           We execute the testcases for each partition
           and for each partition we use one set of CallKeyListSet to filter unnecessary
           test runs
         */
        CallConcurrentCallListFactory<CLASS_UNDER_TEST> factory = new CallConcurrentCallListFactory<>();
        List<List<Integer>> partitionList = new IntegerPartitions().partitions(size);
        for (List<Integer> partition : partitionList) {
            Set<CallKeyListSet> alreadyExecuted = new HashSet<>();
            for (ConcurrentTestCase<CLASS_UNDER_TEST> testCase : concurrentTestCaseList) {
                List<ConcurrentCallList<CLASS_UNDER_TEST>> runnableList = factory.create(partition, testCase.concurrentCallList(), alreadyExecuted);
                if (runnableList != null) {
                    runOneTest(runnableList, testCase,partition);
                }
            }

        }
    }

    private void runOneTest(List<ConcurrentCallList<CLASS_UNDER_TEST>> runnableList, ConcurrentTestCase<CLASS_UNDER_TEST> testCase, List<Integer> partition) {
        AllInterleavings all = new AllInterleavingsBuilder().withMaximumIterations(maximumIterations).build(name + testCase.getLabel() + labelForPartition(partition));
        while (all.hasNext()) {
            CLASS_UNDER_TEST classUnderTest = testCase.createClassUnderTest().get();
            List<CallConcurrentCalls<CLASS_UNDER_TEST>> callConcurrentCallsList = runnableList.stream()
                    .map(list -> new CallConcurrentCalls<>(classUnderTest, list.concurrentCallList(), all))
                    .collect(Collectors.toList());
            List<Thread> threadList = callConcurrentCallsList.stream().map(Thread::new).collect(Collectors.toList());
            for (Thread t : threadList) {
                t.start();
            }
            for (Thread t : threadList) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            for (CallConcurrentCalls<CLASS_UNDER_TEST> call : callConcurrentCallsList) {
                if (!call.isSuccess()) {
                    throw new RuntimeException("");
                }
            }

            for (CheckAfterJoin<CLASS_UNDER_TEST> check : testCase.checkAfterJoinList()) {
                if (!check.readAndCheck(classUnderTest)) {
                    throw new RuntimeException("");
                }
            }

        }
    }


    private String labelForPartition(List<Integer> partition) {
        return " ThreadCount:" + partition.size();
    }
}
