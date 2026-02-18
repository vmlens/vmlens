package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class LockContainerTest {

    /*
    @Test
    public void monitorEnterExit() {
        // Expected
        ListElementEither expectedNode = new ListElementEither(
                new AlternativeOneOrder(lbr(pos(0,1),pos(1,0))) ,
                new AlternativeOneOrder(lbr(pos(1,1),pos(0,0))));

        // Given
        LockKey lockKey = new MonitorKey(1L);
        BlockAndOperationBuilder firstThread = new BlockAndOperationBuilder(0,lockKey);
        BlockAndOperationBuilder secondThread = new BlockAndOperationBuilder(1,lockKey);

        Block firstBlock = firstThread.enter().exit();
        Block secondBlock = secondThread.enter().exit();

        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);
        ListBuilder listBuilder = new ListBuilder();

        // When
        firstBlock.addToAlternatingOrder(secondBlock,context, listBuilder.start());
        OrderList orderList = listBuilder.build(new InterleaveLoopContextBuilder().build(new QueueInMock(),0));

        // Then
        assertThat(orderList.start().hasSameOrder(expectedNode),is(true));
    }

     */

}
