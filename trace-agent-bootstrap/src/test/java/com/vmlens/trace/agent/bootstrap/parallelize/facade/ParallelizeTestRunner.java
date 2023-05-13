package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.loop.AgentLoggerForTest;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class ParallelizeTestRunner {
    public void run(ThreadIndexToElementList<ActionForTest> actualRun, ParallelizeTestMatcher matcher) {
        ParallelizeFacade.parallelizeLoopContainer = new ParallelizeLoopContainer(
                new ParallelizeLoopFactoryImpl(mock(WaitNotifyStrategy.class), new AgentLoggerForTest()));
        Object parallelizeLoopDefinition = new Object();

        int multiplyBy = 2;
        ThreadLocalWrapperMock[] loopThreadState = new ThreadLocalWrapperMock[actualRun.maxThreadIndex() + 1];
        for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
            loopThreadState[i] = new ThreadLocalWrapperMock(i * multiplyBy + 1);
        }

        while(ParallelizeFacade.hasNext(loopThreadState[0],parallelizeLoopDefinition)) {
            matcher.advance();
            ThreadIndexToElementList<ActionForTest> clone = actualRun.safeClone();
            while (!clone.isEmpty()) {
                boolean oneThreadWasActive = false;
                for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                    if (!clone.isEmptyAtIndex(i)) {
                        if (loopThreadState[i].isActive()) {
                            ActionForTest actionForTest = clone.getAndRemoveAtIndex(i);
                            actionForTest.execute(loopThreadState);
                            matcher.executed(actionForTest.position());
                            oneThreadWasActive = true;
                            break;
                        }
                    }
                }
                assertThat(oneThreadWasActive,is(true));
            }
            multiplyBy += 2;
            for(int i = 0; i < actualRun.maxThreadIndex(); i++) {
                loopThreadState[i] = new ThreadLocalWrapperMock(i * multiplyBy + 1);
            }
        }
        matcher.assertExpectedResults();

    }

}
