package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ArrayWithEquals;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ExpectedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.TestConfig;
import org.apache.commons.collections4.bag.HashBag;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParallelizeFunctionalTest {

    public void runTest(TestConfig config) {
        SynchronizationWrapperForRunFactoryMock allInterleavingsRunFactoryMock = new SynchronizationWrapperForRunFactoryMock();
        CallbackStatePerThreadRepository callbackStatePerThreadRepository = new CallbackStatePerThreadRepository();
        ParallelizeFacade parallelizeFacade = new ParallelizeFacade(allInterleavingsRunFactoryMock);
        CallParallelizeFacadeSyncActionVisitor visitor = new CallParallelizeFacadeSyncActionVisitor(parallelizeFacade,
                callbackStatePerThreadRepository, allInterleavingsRunFactoryMock);
        AllInterleavings allInterleavings = new AllInterleavings("test");
        Set<ArrayWithEquals<Long>> actualResult = new HashSet<ArrayWithEquals<Long>>();
        int runs = 0;
        while (parallelizeFacade.advance(callbackStatePerThreadRepository.get(0), allInterleavings)) {
            runs++;
            HashBag<Integer> givenThreadIndices = config.threadIndices();
            SynchronizationWrapperForRunMock currentRun = allInterleavingsRunFactoryMock.getCurrentRun();
            for (Integer threadIndex : givenThreadIndices) {
                callbackStatePerThreadRepository.get(threadIndex).parallelizedThreadLocal =
                        new ParallelizedThreadLocal(currentRun);
                currentRun.getThreadIdToState().begin(threadIndex,
                        callbackStatePerThreadRepository.get(threadIndex).threadId);
            }
            GivenRun givenRun = new GivenRun(config.givenRun);
            List<Long> threadIds = new LinkedList();
            for (int i = 0; i < givenRun.count; i++) {
                Set<Long> givenThreadIds = toThreadIds(callbackStatePerThreadRepository
                        , givenThreadIndices);
                for (Long threadId : givenThreadIds) {
                    if (!allInterleavingsRunFactoryMock.getCurrentRun()
                            .getRunState().needsToWait(threadId)) {
                        givenRun.pop(threadId).accept(visitor);
                        givenThreadIndices.remove(callbackStatePerThreadRepository.threadIndex(threadId));
                        if (!givenThreadIndices.contains(callbackStatePerThreadRepository.threadIndex(threadId))) {
                            currentRun.getThreadIdToState().end(threadId);
                        }
                        threadIds.add(threadId);
                        break;
                    }
                }
            }
            actualResult.add(new ArrayWithEquals<Long>(threadIds.toArray(new Long[0])));
            callbackStatePerThreadRepository.reset();
        }
        Set<ArrayWithEquals<Long>> expectedResult = new HashSet<ArrayWithEquals<Long>>();
        for (ExpectedRun expectedRun : config.expectedRuns) {
            expectedResult.add(new ArrayWithEquals(toThreadIds(callbackStatePerThreadRepository,
                    expectedRun.threadIndices)));
        }
        assertThat(actualResult, is(expectedResult));
        System.out.println("runs " + runs);
    }

    private Set<Long> toThreadIds(CallbackStatePerThreadRepository callbackStatePerThreadRepository,
                                  HashBag<Integer> threadIndices) {
        Set<Long> givenThreadIds = new HashSet<Long>();
        for (Integer index : threadIndices) {
            givenThreadIds.add(callbackStatePerThreadRepository.threadId(index));
        }
        return givenThreadIds;
    }

    public Long[] toThreadIds(CallbackStatePerThreadRepository callbackStatePerThreadRepository, int[] threadIndices) {
        Long[] result = new Long[threadIndices.length];
        int i = 0;
        for (int threadIndex : threadIndices) {
            result[i] = callbackStatePerThreadRepository.threadId(threadIndex);
            i++;
        }
        return result;
    }


}
