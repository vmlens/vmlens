package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ParallelizeTestRunner {
    public void run(ParallelizeTestBuilder builder, ParallelizeTestMatcher matcher)  {
        ParallelizeLoopFactoryForTest parallelizeLoopFactoryForTest = new ParallelizeLoopFactoryForTest();
        Object parallelizeLoopDefinition = new Object();
        ParallelizeFacade.parallelizeLoopContainer = new ParallelizeLoopContainer(parallelizeLoopFactoryForTest);

        ThreadIdToElementList<ActionForTest> actualRun = builder.build();
        ThreadLocalStateForFacade[] loopThreadState = new ThreadLocalStateForFacade[actualRun.maxThreadIndex() + 1];
        for(int i = 0; i <= actualRun.maxThreadIndex(); i++) {
            loopThreadState[i] = new ThreadLocalStateForTest(i);
        }

        while(ParallelizeFacade.hasNext(loopThreadState[0],parallelizeLoopDefinition)) {

            for(int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                loopThreadState[i].createNewParallelizedThreadLocal(parallelizeLoopFactoryForTest.getCurrentRun());
            }

            matcher.advance();
            ThreadIdToElementList<ActionForTest> clone =  actualRun.safeClone();
            while (!clone.isEmpty()) {
                boolean oneThreadWasActive = false;
                for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                    if (!clone.isEmptyAtIndex(i)) {
                        if (parallelizeLoopFactoryForTest.isActive(i)) {
                            ActionForTest actionForTest = clone.getAndRemoveAtIndex(i);
                            actionForTest.execute(loopThreadState[actionForTest.threadIndex()]);
                            matcher.executed(actionForTest.position());
                            oneThreadWasActive = true;
                            break;
                        }
                    }
                }
                assertThat(oneThreadWasActive,is(true));
            }
            for(int i = 0; i < actualRun.maxThreadIndex(); i++) {
                loopThreadState[i] = new ThreadLocalStateForTest(i);
            }
        }
        matcher.assertExpectedResults();
    }

}
