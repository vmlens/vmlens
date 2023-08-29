package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class TestFromBlockContainerToOrderArrays {

    @Test
    public void testMonitor() {
        // Given
        Pair<BlockContainer, TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> testData = TestFixture.monitor();
        OrderArraysFactory factory = new OrderArraysFactory();

        // When
        OrderArrays orderArrays = factory.create(testData.getLeft(),
                mock(ThreadIndexToMaxPosition.class));

        // Expected
        AlternatingOrderElement[] alternatingOrder = new AlternatingOrderElement[1];
        alternatingOrder[0] = new AlternatingOrderElement(lbr(p(0, 1), p(1, 0)),
                lbr(p(1, 1), p(0, 0)));

        // Then
        assertThat(orderArrays.alternatingOrderArray, is(alternatingOrder));

    }

}
