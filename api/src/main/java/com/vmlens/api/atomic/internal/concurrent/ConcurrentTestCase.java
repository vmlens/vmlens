package com.vmlens.api.atomic.internal.concurrent;

import com.vmlens.api.AllInterleavings;

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

    public void run(String name) {
        AllInterleavings all = new AllInterleavings(name +  getLabel());
        while (all.hasNext()) {
            CLASS_UNDER_TEST classUnderTest = createClassUnderTest.get();
            List<CallConcurrentCall<CLASS_UNDER_TEST>> callConcurrentCallList = new LinkedList<>();
            List<Thread> threads = new LinkedList<>();
            for (ConcurrentCall<CLASS_UNDER_TEST> task : concurrentCallList) {
                CallConcurrentCall<CLASS_UNDER_TEST> call = new CallConcurrentCall<>(classUnderTest, task);
                callConcurrentCallList.add(call);
                Thread t = new Thread(call);
                threads.add(t);
                t.start();
            }
            for (Thread t : threads) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            for (CallConcurrentCall<CLASS_UNDER_TEST> call : callConcurrentCallList) {
                if (!call.isSuccess()) {
                    throw new RuntimeException("");
                }
            }


            for (CheckAfterJoin<CLASS_UNDER_TEST> check : checkAfterJoinList) {
                if( ! check.readAndCheck(classUnderTest)) {
                    throw new RuntimeException("");
                }
            }
        }
    }

    private String getLabel() {
        StringBuilder builder = new StringBuilder();
        for(ConcurrentCall<CLASS_UNDER_TEST> concurrentCall : concurrentCallList ) {
            builder.append(concurrentCall.getLabel());
        }
        return builder.toString();
    }

}
