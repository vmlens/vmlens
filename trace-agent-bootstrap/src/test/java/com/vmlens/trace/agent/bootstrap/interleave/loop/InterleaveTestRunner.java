package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestRunner {
    public void run(ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun, InterleaveTestMatcher matcher)  {
        InterleaveLoop loop = new InterleaveLoop(new AgentLoggerForTest());
        Iterator<ActualRun> iter = loop.iterator();
        while(iter.hasNext()) {
            matcher.advance();
            ActualRun calculatedRun = iter.next();
            ThreadIdToElementList<InterleaveActionWithPositionFactory> clone =  actualRun.safeClone();
            while (!clone.isEmpty()) {
                boolean oneThreadWasActive = false;
                for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                    if (!clone.isEmptyAtIndex(i)) {
                        if (calculatedRun.isActive(i)) {
                            InterleaveActionWithPositionFactory interleaveActionWithPosition = clone.getAndRemoveAtIndex(i);
                            calculatedRun.after(interleaveActionWithPosition);
                            matcher.executed(interleaveActionWithPosition.threadIndex());
                            oneThreadWasActive = true;
                            break;
                        }
                    }
                }
                assertThat(oneThreadWasActive,is(true));
            }
        }
        matcher.assertExpectedResults();
    }
}
