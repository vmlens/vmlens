package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveTestRunner {
    public void run(InterleaveTestBuilder builder, InterleaveTestMatcher matcher)  {
        ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun = builder.build();
        InterleaveLoop loop = new InterleaveLoop();
        Iterator<CalculatedRun> iter = loop.iterator();
        while(iter.hasNext()) {
            matcher.advance();
            CalculatedRun calculatedRun = iter.next();
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
