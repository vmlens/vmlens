package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.RunMock;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CallbackActionForRuntimeEventTest {

    @Test
    public void methodEnter() {
        // Given
        int THREAD_INDEX = 3;

        MethodEnterEvent methodEnterEvent = new MethodEnterEvent(5);
        CallbackActionForRuntimeEvent<MethodEnterEvent> callbackActionForRuntimeEvent =
                new CallbackActionForRuntimeEvent<>(methodEnterEvent,
                        new SetFieldsStrategyNoOp<MethodEnterEvent>());

        // When
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                callbackActionForRuntimeEvent.execute(new ThreadLocalWhenInTest(new RunMock(), THREAD_INDEX));

        // Then
        assertThat(serializableEvents.size(), is(1));
    }
}
