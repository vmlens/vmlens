package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorKey;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class LockContainerTest {

    @Test
    public void monitorEnterExit() {
        // Expected
        SingleChildNode expectedNode = new SingleChildNode(null,
                new AlternativeOneOrder(lbr(pos(0,1),pos(1,0))) ,
                new AlternativeOneOrder(lbr(pos(1,1),pos(0,0))));

        // Given
        LockKey lockKey = new MonitorKey(1L);
        BlockAndOperationBuilder firstThread = new BlockAndOperationBuilder(0,lockKey);
        BlockAndOperationBuilder secondThread = new BlockAndOperationBuilder(1,lockKey);

        Block firstBlock = firstThread.enter().exit();
        Block secondBlock = secondThread.enter().exit();

        BuildAlternatingOrderContext context = mock(BuildAlternatingOrderContext.class);
        TreeBuilder treeBuilder = new TreeBuilder();

        // When
        firstBlock.addToAlternatingOrder(secondBlock,context,treeBuilder.start());
        OrderTree orderTree = treeBuilder.build();

        // Then
        assertThat(orderTree.start().hasSameOrder(expectedNode),is(true));
    }

}
