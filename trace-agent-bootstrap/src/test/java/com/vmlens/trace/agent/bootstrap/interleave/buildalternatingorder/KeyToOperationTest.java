package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey.VolatileKey;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeyToOperationTest {

    @Test
    public void volatileAccess() {
        // Expected
        SingleChildNode expectedNode = new SingleChildNode(null,
                new AlternativeOneOrder(lbr(pos(0,0),pos(1,0))) ,
                new AlternativeOneOrder(lbr(pos(1,0),pos(0,0))));

        // Given
        VolatileFieldKey volatileFieldKey = new VolatileFieldKey(1,1L);
        VolatileAccess read = new VolatileAccess(0, volatileFieldKey, MemoryAccessType.IS_READ);
        VolatileAccess write = new VolatileAccess(1, volatileFieldKey, MemoryAccessType.IS_WRITE);

        KeyToOperation<VolatileKey, DependentOperationAndPosition<VolatileAccess>> keyToOperation = new KeyToOperation<>();
        keyToOperation.put(read.key(),new DependentOperationAndPosition<>(pos(0,0) , read));
        keyToOperation.put(write.key(),new DependentOperationAndPosition<>(pos(1,0) , write));

        TreeBuilder orderTreeBuilder = new TreeBuilder();
        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);

        // When
        keyToOperation.process(context, orderTreeBuilder.start());
        OrderTree orderTree = orderTreeBuilder.build();

        // Then
        assertThat(orderTree.start().hasSameOrder(expectedNode),is(true));
    }

    @Test
    public void barrierWaitNotify() {
        // Expected
        SingleChildNode expectedNode = new SingleChildNode(null,
                new AlternativeOneOrder(lbr(pos(0,0),pos(1,0))),
                new AlternativeTwoOrders(lbr(pos(1,0),pos(0,0)),
                                         lbr(pos(0,0),pos(1,1))));

        // Given
        FutureKey futureKey = new FutureKey(1L);
        BarrierNotify barrierNotify = new BarrierNotify(1, futureKey);
        BarrierWait barrierWait = new BarrierWait(1, futureKey);

        KeyToOperation<BarrierKey, DependentOperationAndPosition<Barrier>> keyToOperation = new KeyToOperation<>();
        keyToOperation.put(barrierNotify.key(),new DependentOperationAndPosition<>(pos(0,0) , barrierNotify));
        keyToOperation.put(barrierWait.key(),new DependentOperationAndPosition<>(pos(1,0) , barrierWait));

        TreeBuilder orderTreeBuilder = new TreeBuilder();
        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);
        when(context.getLastPositionForThreadIndex(1)).thenReturn(2);

        // When
        keyToOperation.process(context, orderTreeBuilder.start());
        OrderTree orderTree = orderTreeBuilder.build();

        // Then
        assertThat(orderTree.start().hasSameOrder(expectedNode),is(true));

    }

}
