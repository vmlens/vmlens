package com.vmlens.api.testbuilder.internal.runner;

import com.vmlens.api.testbuilder.internal.callkey.CallKey;
import com.vmlens.api.testbuilder.internal.concurrent.ConcurrentCall;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Creates a runnable list from a partition and ConcurrentCall list
 */
public class CallConcurrentCallListFactory<CLASS_UNDER_TEST> {

    /**
     * returns null when the created run is inside alreadyExecuted
     */
    public List<ConcurrentCallList<CLASS_UNDER_TEST>> create(List<Integer> partition,
                                                              List<ConcurrentCall<CLASS_UNDER_TEST>> concurrentCallList,
                                                              Set<CallKeyListSet> alreadyExecuted) {
        List<ConcurrentCallList<CLASS_UNDER_TEST>> result = new LinkedList<>();
        CallKeyListSet callKeyListSet = new CallKeyListSet();
        int currentIndex = 0;
        for(int numberOfCalls : partition) {
            List<ConcurrentCall<CLASS_UNDER_TEST>> forOneRunner = new LinkedList<>();
            for(int i = 0; i < numberOfCalls; i++) {
                forOneRunner.add(concurrentCallList.get(currentIndex));
                currentIndex++;
            }
            result.add(new ConcurrentCallList<>(forOneRunner));
            List<CallKey> callKeyList = forOneRunner.stream().map(ConcurrentCall::callKey).collect(Collectors.toList());
            callKeyListSet.add(callKeyList);
        }
        if(alreadyExecuted.contains(callKeyListSet)) {
            return null;
        }
        alreadyExecuted.add(callKeyListSet);
        return result;
    }

}
