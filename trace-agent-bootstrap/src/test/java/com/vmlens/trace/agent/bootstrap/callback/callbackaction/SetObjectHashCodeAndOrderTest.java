package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCodeAndOrder;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test to check the generic structure of
 */
public class SetObjectHashCodeAndOrderTest {


    @Test
    public void setHashCode() {
        // Given
        final int THREAD_INDEX = 5;
        final int ORDER = 7;
        final Object onObject = new Object();

        Run runMock = mock(Run.class);
        when(runMock.after(any(), any())).thenAnswer(invocationOnMock ->
                RuntimeEventAndWarnings.of((RuntimeEvent) invocationOnMock.getArguments()[0]));

        VolatileAccessEvent volatileAccessEvent = new VolatileAccessEvent();

        OrderMap<ObjectHashCodeAndFieldId> orderMapMock = mock(OrderMap.class);
        when(orderMapMock.getAndIncrementOrder(any())).thenReturn(ORDER);

        RunAfter<VolatileAccessEvent> callbackActionForRuntimeEvent =
                new RunAfter<>(volatileAccessEvent,
                        new SetObjectHashCodeAndOrder<>(orderMapMock, onObject));

        // When
        callbackActionForRuntimeEvent.execute(new ThreadLocalWhenInTest(runMock, THREAD_INDEX));

        // Then
        VolatileAccessEvent expected = new VolatileAccessEvent();
        expected.setObjectHashCode(System.identityHashCode(onObject));
        expected.setOrder(ORDER);
        expected.setThreadIndex(THREAD_INDEX);

        assertThat(volatileAccessEvent, is(expected));
    }
}
