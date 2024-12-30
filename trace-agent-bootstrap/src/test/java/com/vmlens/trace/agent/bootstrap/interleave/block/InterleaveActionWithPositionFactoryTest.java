package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.BlockBuilderNoOpWithThreadIndexGuineaPig;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InterleaveActionWithPositionFactoryTest {

    @Test
    public void addBlockBuilder() {
        // Given
        InterleaveAction threadIndex_0_First = new BlockBuilderNoOpWithThreadIndexGuineaPig(0);
        InterleaveAction threadIndex_1_First = new BlockBuilderNoOpWithThreadIndexGuineaPig(1);
        InterleaveAction threadIndex_0_Second = new BlockBuilderNoOpWithThreadIndexGuineaPig(0);

        TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = new TLinkedList<>();
        actualRun.add(wrap(threadIndex_0_First));
        actualRun.add(wrap(threadIndex_1_First));
        actualRun.add(wrap((threadIndex_0_Second)));

        InterleaveActionWithPositionFactory blockBuilderFactory = new InterleaveActionWithPositionFactory();
        // When
        Pair<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>, ThreadIndexToElementList<Position>>
                result = blockBuilderFactory.create(actualRun);

        // Then
        assertThat(result.getLeft().get(0).element().element(), is((InterleaveAction) threadIndex_0_First));
        assertThat(result.getLeft().get(0).element().position(), is((pos(0, 0))));
        assertThat(result.getLeft().get(1).element().position(), is((pos(1, 0))));
        assertThat(result.getLeft().get(2).element().position(), is((pos(0, 1))));
    }
}
