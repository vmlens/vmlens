package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.BlockBuilderNoOpWithThreadIndexGuineaPig;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndThreadIndexToPositionLists;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderWithThreadIndex;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrapp;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class BlockBuilderWithPositionFactoryTest {

    @Test
    public void addBlockBuilder() {
        // Given
        BlockBuilderWithThreadIndex threadIndex_0_First = new BlockBuilderNoOpWithThreadIndexGuineaPig(0);
        BlockBuilderWithThreadIndex threadIndex_1_First = new BlockBuilderNoOpWithThreadIndexGuineaPig(1);
        BlockBuilderWithThreadIndex threadIndex_0_Second = new BlockBuilderNoOpWithThreadIndexGuineaPig(0);

        TLinkedList<TLinkableWrapper<BlockBuilderWithThreadIndex>> actualRun = new TLinkedList<TLinkableWrapper<BlockBuilderWithThreadIndex>>();
        actualRun.add(wrapp(threadIndex_0_First));
        actualRun.add(wrapp(threadIndex_1_First));
        actualRun.add(wrapp((threadIndex_0_Second)));

        BlockBuilderWithPositionFactory blockBuilderFactory = new BlockBuilderWithPositionFactory();
        // When
        BlockBuilderAndThreadIndexToPositionLists result = blockBuilderFactory.create(actualRun);

        // Then
        assertThat(result.runWithPosition.get(0).element.element(), is((BlockBuilder) threadIndex_0_First));
        assertThat(result.runWithPosition.get(0).element.position(), is((pos(0, 0))));
        assertThat(result.runWithPosition.get(1).element.position(), is((pos(1, 0))));
        assertThat(result.runWithPosition.get(2).element.position(), is((pos(0, 1))));
    }
}
