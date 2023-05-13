package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.InterleaveTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestRunner {
    public void run(ThreadIndexToElementList<InterleaveActionWithPositionFactory> actualRun, InterleaveTestMatcher matcher) {
        InterleaveLoop loop = new InterleaveLoop(new AgentLoggerForTest());
        Iterator<CalculatedRun> iter = loop.iterator();
        while (iter.hasNext()) {
            matcher.advance();
            CalculatedRun calculatedRun = iter.next();
            ThreadIndexToElementList<InterleaveActionWithPositionFactory> clone = actualRun.safeClone();
            while (!clone.isEmpty()) {
                boolean oneThreadWasActive = false;
                for (int i = 0; i <= actualRun.maxThreadIndex(); i++) {
                    if (!clone.isEmptyAtIndex(i)) {
                        if (calculatedRun.isActive(i)) {
                            InterleaveActionWithPositionFactory interleaveActionWithPosition = clone.getAndRemoveAtIndex(i);
                            // ToDo Fix
                            //  calculatedRun.after(interleaveActionWithPosition);
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
