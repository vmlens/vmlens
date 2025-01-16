package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CallbackActionStartAtomicVolatileFieldAccessTest {

    @Test
    public void execute() {
        // Given
        int THREAD_INDEX = 3;
        Run runMock = mock(Run.class);
        RuntimeEventAndSetFieldsStrategy atomicOperation = mock(RuntimeEventAndSetFieldsStrategy.class);
        CallbackActionStartAtomicVolatileFieldAccess callbackActionStartAtomicVolatileFieldAccess =
                new CallbackActionStartAtomicVolatileFieldAccess(atomicOperation);

        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(runMock, THREAD_INDEX);

        // When
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents =
                callbackActionStartAtomicVolatileFieldAccess.execute(threadLocalWhenInTest);

        // Then
        verify(runMock).startAtomicOperation(threadLocalWhenInTest);
        assertThat(threadLocalWhenInTest.atomicOperation(), is(atomicOperation));
        assertThat(serializableEvents.size(), is(0));
    }

}
