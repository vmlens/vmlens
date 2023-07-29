package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcherBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;

public class TestFromAlternatingOrderContainerToCalculatedRun {

    @Test
    public void volatileReadAndWrite() {
        // Given
        Triple<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>, AlternatingOrderContainer, FeatureTestMatcher>
                testData = TestFixture.volatileReadAndWrite();

        // When
        Iterator<CalculatedRun> iterator = testData.getMiddle().iterator();

        // Then
        while (iterator.hasNext()) {
            CalculatedRun run = iterator.next();
            for (Position pos : run.calculatedRunElementArray()) {
                testData.getRight().executed(pos.threadIndex);
            }
            testData.getRight().advance();
        }
        testData.getRight().assertExpectedResults();
    }

    @Test
    public void fixedAndAlternating() {
        // Expected
        FeatureTestMatcherBuilder featureTestMatcherBuilder = new FeatureTestMatcherBuilder();
        featureTestMatcherBuilder.always(p(1, 2), p(0, 7));
        featureTestMatcherBuilder.always(p(0, 1), p(1, 0));
        FeatureTestMatcher matcher = featureTestMatcherBuilder.build();

        // Given
        AlternatingOrderContainerTestBuilder builder = new AlternatingOrderContainerTestBuilder();
        builder.fixed(p(1, 2), p(0, 7));
        builder.fixed(p(0, 1), p(1, 0));
        builder.both(p(0, 0), p(1, 0));
        builder.both(p(0, 2), p(1, 0));

        AlternatingOrderContainer container = builder.build();

        // When
        Iterator<CalculatedRun> iterator = container.iterator();

        // Then
        while (iterator.hasNext()) {
            CalculatedRun run = iterator.next();
            if (run != null) {
                matcher.executed(run.calculatedRunElementArray());
                System.out.println(Arrays.toString(run.calculatedRunElementArray()));
            }
        }
    }


}
