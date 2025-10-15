package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierWaitEnter;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.MethodIdByteCodePositionAndThreadIndexFactory.threadIndex;
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
        VolatileAccess read = new VolatileAccess(threadIndex(0), volatileFieldKey, MemoryAccessType.IS_READ);
        VolatileAccess write = new VolatileAccess(threadIndex(1), volatileFieldKey, MemoryAccessType.IS_WRITE);

        KeyToOperation<VolatileKey, DependentOperationAndPosition<VolatileAccess>> keyToOperation = new KeyToOperation<>();
        keyToOperation.put(read.key(),new DependentOperationAndPosition<>(pos(0,0) , read));
        keyToOperation.put(write.key(),new DependentOperationAndPosition<>(pos(1,0) , write));

        TreeBuilder orderTreeBuilder = new TreeBuilder();
        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);

        // When
        keyToOperation.process(context, orderTreeBuilder.start());
        OrderTree orderTree = orderTreeBuilder.build(new InterleaveLoopContextBuilder().build(new QueueInMock(),0));

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
        BarrierNotify barrierNotify = new BarrierNotify(threadIndex(1), futureKey);
        BarrierWaitEnter barrierWait = new BarrierWaitEnter(threadIndex(1), futureKey);

        KeyToOperation<BarrierKey, DependentOperationAndPosition<Barrier>> keyToOperation = new KeyToOperation<>();
        keyToOperation.put(barrierNotify.key(),new DependentOperationAndPosition<>(pos(0,0) , barrierNotify));
        keyToOperation.put(barrierWait.key(),new DependentOperationAndPosition<>(pos(1,0) , barrierWait));

        TreeBuilder orderTreeBuilder = new TreeBuilder();
        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);
        when(context.getLastPositionForThreadIndex(1)).thenReturn(2);

        // When
        keyToOperation.process(context, orderTreeBuilder.start());
        OrderTree orderTree = orderTreeBuilder.build(new InterleaveLoopContextBuilder().build(new QueueInMock(),0));

        // Then
        assertThat(orderTree.start().hasSameOrder(expectedNode),is(true));

    }

}
