package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

public class TestFromBlockListToBlockContainer {

    @Test
    public void testMonitor() {
        // Given
        Pair<BlockContainer, TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> testData = TestFixture.monitor();

        // When
        BlockContainer result = new BlockContainerFactory().create(testData.getRight());

        // Then
        assertThat(result, is(testData.getLeft()));

    }

    @Test
    public void testChainedMonitor() {
        // Given
        TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList = TestFixture.chainedMonitor();

        // When
        BlockContainer result = new BlockContainerFactory().create(blockBuilderList);

        // Then
        ThreadIndexToElementList<DependentBlock> threadIndexToElementList = result.dependentBlocks().get(new MonitorKey(0));
        Iterator<TLinkableWrapper<DependentBlock>> iterator = threadIndexToElementList.listAt(0).iterator();
        List<LeftBeforeRight> blocks = new LinkedList<>();

        while (iterator.hasNext()) {
            DependentBlock current = iterator.next().element;
            blocks.add(lbr(current.start().position(), current.end().position()));
        }

        assertThat(blocks, containsInAnyOrder(lbr(p(0, 0), p(0, 3)),
                lbr(p(0, 1), p(0, 2))));


    }

}
