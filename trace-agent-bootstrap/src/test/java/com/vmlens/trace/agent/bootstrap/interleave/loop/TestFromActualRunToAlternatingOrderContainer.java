package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFromActualRunToAlternatingOrderContainer {
    @Test
    public void volatileReadAndWrite() {
        // Given
        Triple<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>, AlternatingOrderContainer, FeatureTestMatcher>
                testData = TestFixture.volatileReadAndWrite();

        // When
        AlternatingOrderContainer alternatingOrderContainer = InterleaveLoop.create(testData.getLeft());

        // Then
        assertThat(alternatingOrderContainer, is(testData.getMiddle()));
    }

    @Test
    public void threadJoin() {
        // Given
        Pair<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>, AlternatingOrderContainer> testData =
                TestFixture.threadJoin();

        // When
        AlternatingOrderContainer alternatingOrderContainer = InterleaveLoop.create(testData.getLeft());

        // Then
        assertThat(alternatingOrderContainer, is(testData.getRight()));
    }

    @Test
    public void testVolatileIncrement() {

    }


}
