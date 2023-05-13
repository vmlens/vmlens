package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class TestCreateInterleaveAction {

    @Test
    public void testCreateThreadStartAndBegin() {
        // Expected Result
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> expectedRun = new
                TLinkedList<>();
        expectedRun.add(new TLinkableWrapper(new ElementAndPosition<Object>(new ThreadStart(1),new Position(0, 0))));
        // Given
        BlockBuilderAndCalculatedRunElementContainerFactory blockBuilderAndCalculatedRunElementContainerFactory =
                new BlockBuilderAndCalculatedRunElementContainerFactory();
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun =
                new TLinkedList<>();
        actualRun.add(new TLinkableWrapper(new ThreadStartFactory(0,1)));
        // When
        BlockBuilderAndCalculatedRunElementContainer container = blockBuilderAndCalculatedRunElementContainerFactory.create(actualRun);
        // Then
        assertThat(container.runWithPosition,containsInAnyOrder(expectedRun.toArray()));
    }

}
