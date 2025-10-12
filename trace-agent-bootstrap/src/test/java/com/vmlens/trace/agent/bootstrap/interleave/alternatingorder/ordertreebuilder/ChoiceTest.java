package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeNoOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.TwoChildrenNode;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class ChoiceTest {

    @Test
    public void fill() {
        // Given
        Choice choice = new Choice();
        choice.alternativeA().either(new AlternativeNoOrder(true),new AlternativeNoOrder(true));

        // When
        OrderTreeNode order = choice.build(new OrderTreeBuilderContext(new InterleaveLoopContextBuilder().build(new QueueInMock(),0)));

        // Then
        assertThat(order,instanceOf(TwoChildrenNode.class));
    }

}
