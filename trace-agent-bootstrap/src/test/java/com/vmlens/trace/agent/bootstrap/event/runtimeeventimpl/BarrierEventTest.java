package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierKeyTypeFuture;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierTypeWait;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;

public class BarrierEventTest {

    @Test
    public void createInterleaveActionNotify() {
        // Expected
        Object object = new Object();

        BarrierWait barrierWait = new BarrierWait(1, new FutureKey(System.identityHashCode(object)));

        // Given
        CreateInterleaveActionContext createInterleaveActionContext = mock(CreateInterleaveActionContext.class);

        BarrierEvent wait = new BarrierEvent(BarrierTypeWait.SINGLETON,
                BarrierKeyTypeFuture.SINGLETON,
                object);

        // When
        wait.setInMethodIdAndPosition(4,7, null);
        InterleaveAction action = wait.create(createInterleaveActionContext);

        // Then
        assertThat(action,is(barrierWait));
    }

}
