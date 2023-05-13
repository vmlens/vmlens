package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import org.junit.Test;

import java.util.Iterator;

public class TestFromAlternatingOrderContainerToCalculatedRun {

    @Test
    public void volatileReadAndWrite() {
        // Given
        AlternatingOrderContainer alternatingOrderContainer = AlternatingOrderContainerJsonMemento.load("volatileReadAndWrite");
        InterleaveTestMatcher matcher = new InterleaveTestMatcher();
        matcher.leftBeforeRight(new Position(0, 0), new Position(1, 0));
        matcher.leftBeforeRight(new Position(1, 0), new Position(0, 0));
        matcher.runs(2);

        // When
        Iterator<CalculatedRun> iterator = alternatingOrderContainer.iterator();

        // Then
        while (iterator.hasNext()) {
            CalculatedRun run = iterator.next();
            for (Position pos : run.calculatedRunElementArray()) {
                matcher.executed(pos.threadIndex);
            }
            matcher.advance();
        }
        matcher.assertExpectedResults();
    }

}
