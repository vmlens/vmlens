package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestData;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import org.junit.Test;

import java.util.Iterator;

public class TestFromAlternatingOrderContainerToCalculatedRun {

    @Test
    public void volatileReadAndWrite() {
        // Given
        TestData testData = TestFixture.volatileReadAndWrite();

        // When
        Iterator<CalculatedRun> iterator = testData.alternatingOrderContainer().iterator();

        // Then
        while (iterator.hasNext()) {
            CalculatedRun run = iterator.next();
            for (Position pos : run.calculatedRunElementArray()) {
                testData.calculatedRunMatcher().executed(pos.threadIndex);
            }
            testData.calculatedRunMatcher().advance();
        }
        testData.calculatedRunMatcher().assertExpectedResults();
    }

}
