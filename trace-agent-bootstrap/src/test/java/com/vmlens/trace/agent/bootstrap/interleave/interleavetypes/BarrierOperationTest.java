package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeTwoOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation.DependentOperationAndPosition;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class BarrierOperationTest {

    private final Position notifyPosition = pos(1,3);
    private final Position waitPosition = pos(0,0);

    private final FutureKey futureKey = new FutureKey(5L);
    private final BarrierNotify notify = new BarrierNotify(futureKey);
    private final BarrierWait wait = new BarrierWait(futureKey);

    @Test
    public void testAsymmetricWaitNotify() {
        runTestAsymmetricWaitNotify(wait,waitPosition,notify,notifyPosition);
        runTestAsymmetricWaitNotify(notify,notifyPosition,wait,waitPosition);

    }

    private void runTestAsymmetricWaitNotify(Barrier firstBarrier, Position firstPosition,
                                             Barrier secondBarrier,Position secondPosition) {
        // expected
        AlternativeOneOrder alternativeOneOrder = new AlternativeOneOrder(lbr(notifyPosition,waitPosition));
        AlternativeTwoOrders alternativeTwoOrders = new AlternativeTwoOrders(lbr(waitPosition,notifyPosition),
                lbr(notifyPosition,pos(0,1)));

        // Given
        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);
        when(context.getLastPositionForThreadIndex(eq(0))).thenReturn(5);

        TreeBuilderNode treeBuilderNode = mock(TreeBuilderNode.class);

        // When
        firstBarrier.addToAlternatingOrder(firstPosition,
                new DependentOperationAndPosition<>(secondPosition,secondBarrier),
                context,
                treeBuilderNode);

        // Then
        verify(treeBuilderNode).either(eq(alternativeOneOrder),eq(alternativeTwoOrders));
    }


}
