package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.*;

public class CallbackActionEndAtomicOperationTest {

    @Test
    public void execute() {
        // Given
        int THREAD_INDEX = 3;
        Run runMock = mock(Run.class);
        when(runMock.after(any(), any())).thenReturn(empty());

        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEventList =
                mock(TLinkedList.class);

        CallbackAction atomicOperation = mock(CallbackAction.class);
        when(atomicOperation.execute(any())).thenReturn(serializableEventList);

        CallbackActionEndAtomicOperation callbackActionEndAtomicOperation =
                new CallbackActionEndAtomicOperation();

        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(runMock, THREAD_INDEX);
        threadLocalWhenInTest.setAtomicVolatileFieldAccess(atomicOperation);

        // When
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                callbackActionEndAtomicOperation.execute(threadLocalWhenInTest);

        // Then
        verify(atomicOperation).execute(any());
        assertThat(threadLocalWhenInTest.atomicOperation(), is(nullValue()));
        assertThat(serializableEvents, is(serializableEventList));
    }
}
