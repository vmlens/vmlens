package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackActionForRuntimeEventTest {

    @Test
    public void methodEnter() {
        // Given
        int THREAD_INDEX = 3;

        Run runMock = mock(Run.class);
        when(runMock.after(any(), any())).thenAnswer(invocationOnMock ->
                RuntimeEventAndWarnings.of((RuntimeEvent) invocationOnMock.getArguments()[0]));

        MethodEnterEvent methodEnterEvent = new MethodEnterEvent(5);
        CallbackActionForRuntimeEvent<MethodEnterEvent> callbackActionForRuntimeEvent =
                new CallbackActionForRuntimeEvent<>(methodEnterEvent,
                        new SetFieldsStrategyNoOp<>());

        // When
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                callbackActionForRuntimeEvent.execute(new ThreadLocalWhenInTest(runMock, THREAD_INDEX));

        // Then
        assertThat(serializableEvents.size(), is(1));
    }
}
