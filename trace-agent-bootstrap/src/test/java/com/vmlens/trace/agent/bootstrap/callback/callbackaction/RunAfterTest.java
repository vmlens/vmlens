package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.singleton;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunAfterTest {

    @Test
    public void methodEnter() {
        // Given
        List<SerializableEvent> serializableEvents = new LinkedList<>();

        int THREAD_INDEX = 3;

        Run runMock = mock(Run.class);
        when(runMock.after(any(), any())).thenAnswer(invocationOnMock ->
                singleton((RuntimeEvent) invocationOnMock.getArguments()[0]));

        MethodEnterEvent methodEnterEvent = new MethodEnterEvent(5);
        RunAfter<MethodEnterEvent> callbackActionForRuntimeEvent =
                new RunAfter<>(methodEnterEvent,
                        new SetFieldsNoOp<>());

        // When
                callbackActionForRuntimeEvent.execute(new ThreadLocalWhenInTest(runMock, THREAD_INDEX),
                        new QueueInMock(serializableEvents));

        // Then
        assertThat(serializableEvents.size(), is(1));
    }
}
