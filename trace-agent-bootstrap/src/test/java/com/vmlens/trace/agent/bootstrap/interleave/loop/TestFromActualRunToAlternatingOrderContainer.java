package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.testFixture.TestData;
import com.vmlens.trace.agent.bootstrap.testFixture.VolatileFixture;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFromActualRunToAlternatingOrderContainer {

    // Fixme
    //@Test
    public void volatileReadAndWrite() {
        // Given
        TestData testData = new VolatileFixture().volatileReadAndWrite();

        // When
        AlternatingOrderContainer alternatingOrderContainer = new AlternatingOrderContainerFactory().create(testData.
                resultTestBuilder().factoryList());

        // Then
        assertThat(alternatingOrderContainer, is(testData.alternatingOrderContainer()));
    }

    // Fixme
    //@Test
    public void threadJoin() {
        // Given
        Pair<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>, AlternatingOrderContainer> testData =
                TestFixture.threadJoin();

        // When
        AlternatingOrderContainer alternatingOrderContainer = new AlternatingOrderContainerFactory().create(testData.getLeft());

        // Then
        assertThat(alternatingOrderContainer, is(testData.getRight()));
    }

    @Test
    public void testVolatileIncrement() {

    }


}
