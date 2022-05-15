package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.parallelize.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ArrayWithEquals;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.ExpectedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.SyncActionModel;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.TestConfig;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParallelizeFunctionalTest {

    public void runTest(TestConfig config) {
        AllInterleavingsRunFactoryMock allInterleavingsRunFactoryMock = new AllInterleavingsRunFactoryMock();
        CallbackStatePerThreadRepository callbackStatePerThreadRepository = new CallbackStatePerThreadRepository();
        ParallelizeFacade parallelizeFacade = new ParallelizeFacade(allInterleavingsRunFactoryMock);
        CallParallelizeFacadeSyncActionVisitor visitor = new CallParallelizeFacadeSyncActionVisitor(parallelizeFacade,
                callbackStatePerThreadRepository);
        AllInterleavings allInterleavings = new AllInterleavings("test");
        Set<ArrayWithEquals<Long>> actualResult = new HashSet<ArrayWithEquals<Long>>();
        Set<Long> givenThreadIds = config.threadIds(callbackStatePerThreadRepository);
        while (parallelizeFacade.advance(callbackStatePerThreadRepository.get(0), allInterleavings)) {
            List<Long> threadIds = new LinkedList();
            for (SyncActionModel syncActionModel : config.givenRun) {
                syncActionModel.accept(visitor);
                for (Long threadId : givenThreadIds) {
                    if (!allInterleavingsRunFactoryMock.getAllInterleavingsRunMock().getRunState().needsToWait(threadId)) {
                        threadIds.add(threadId);
                        break;
                    }
                }
            }
            actualResult.add(new ArrayWithEquals<Long>(threadIds.toArray(new Long[0])));
        }
        Set<ArrayWithEquals<Long>> expectedResult = new HashSet<ArrayWithEquals<Long>>();
        for (ExpectedRun expectedRun : config.expectedRuns) {
            expectedResult.add(new ArrayWithEquals(expectedRun.toThreadIds(callbackStatePerThreadRepository)));
        }
        assertThat(actualResult, is(expectedResult));


    }


}
