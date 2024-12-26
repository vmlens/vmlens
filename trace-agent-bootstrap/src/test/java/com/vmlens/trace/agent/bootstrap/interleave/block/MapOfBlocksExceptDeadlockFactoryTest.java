package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.BlockBuilderGuineaPig;
import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.DependentBlockElementGuineaPig;
import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.IndependentBlockElementNoOpGuineaPig;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition.epos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapOfBlocksExceptDeadlockFactoryTest {

    @Test
    public void dependentBlocks() {
        // Given
        Object firstKey = new Object();
        DependentBlockElementGuineaPig firstBlockElement = new DependentBlockElementGuineaPig();
        DependentBlockElementGuineaPig secondBlockElement = new DependentBlockElementGuineaPig();

        DependentBlock firstBlock = new DependentBlock(epos(firstBlockElement, 0, 0),
                epos(firstBlockElement, 0, 0));
        DependentBlock secondBlock = new DependentBlock(epos(secondBlockElement, 0, 2),
                epos(secondBlockElement, 0, 2));

        Object secondKey = new Object();
        DependentBlockElementGuineaPig thirdBlockElement = new DependentBlockElementGuineaPig();
        DependentBlock thirdBlock = new DependentBlock(epos(thirdBlockElement, 0, 3),
                epos(secondBlockElement, 0, 3));


        TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> actualRun
                = new TLinkedList<>();

        actualRun.add(TLinkableWrapper.<ElementAndPosition<BlockBuilder>>wrap(ElementAndPosition.<BlockBuilder>epos(
                new BlockBuilderGuineaPig(firstBlock, firstKey), 0, 0)));
        actualRun.add(TLinkableWrapper.<ElementAndPosition<BlockBuilder>>wrap(ElementAndPosition.<BlockBuilder>epos(
                new BlockBuilderGuineaPig(secondBlock, firstKey), 0, 2)));
        actualRun.add(TLinkableWrapper.<ElementAndPosition<BlockBuilder>>wrap(ElementAndPosition.<BlockBuilder>epos(
                new BlockBuilderGuineaPig(thirdBlock, secondKey), 0, 2)));

        MapOfBlocksExceptDeadlockFactory factory = new MapOfBlocksExceptDeadlockFactory();

        // When
        MapOfBlocks result = factory.create(actualRun);

        // Then
        assertThat(result.dependentBlocks().get(firstKey).getElementNAtIndex(0, 0), is(firstBlock));
        assertThat(result.dependentBlocks().get(firstKey).getElementNAtIndex(0, 1), is(secondBlock));

        assertThat(result.dependentBlocks().get(secondKey).getElementNAtIndex(0, 0), is(thirdBlock));
    }

    @Test
    public void inDependentBlocks() {
        // Given
        ElementAndPosition<IndependentBlock> first =
                epos(new IndependentBlockElementNoOpGuineaPig(), 0, 0);
        ElementAndPosition<IndependentBlock> second =
                epos(new IndependentBlockElementNoOpGuineaPig(), 0, 2);


        TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> actualRun
                = new TLinkedList<>();

        actualRun.add(TLinkableWrapper.<ElementAndPosition<BlockBuilder>>wrap(ElementAndPosition.<BlockBuilder>epos(
                new BlockBuilderGuineaPig(first), 0, 0)));
        actualRun.add(TLinkableWrapper.<ElementAndPosition<BlockBuilder>>wrap(ElementAndPosition.<BlockBuilder>epos(
                new BlockBuilderGuineaPig(second), 0, 2)));

        MapOfBlocksExceptDeadlockFactory factory = new MapOfBlocksExceptDeadlockFactory();

        // When
        MapOfBlocks result = factory.create(actualRun);

        // Test
        assertThat(result.inDependentBlocks().get(0).element().element(), is(first.element()));
        assertThat(result.inDependentBlocks().get(1).element().element(), is(second.element()));
    }


}
