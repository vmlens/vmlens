package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.testfixture.QueueInMock;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackStateImplTest {

    @Test
    public void processRuntimeEvent() {
        // Given
        int THREAD_INDEX = 6;

        CallbackStateImpl callbackStateImpl = new CallbackStateImpl();
        RuntimeEventFactory runtimeEventFactoryMock = mock(RuntimeEventFactory.class);
        when(runtimeEventFactoryMock.create()).thenReturn(new VolatileAccessEvent());

        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L);
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(new RunMock(), THREAD_INDEX);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);


        List<SerializableEvent> eventList = new LinkedList<>();
        QueueIn queueIn = new QueueInMock(eventList);

        // When
        callbackStateImpl.processRuntimeEventFactory(runtimeEventFactoryMock, threadLocalForParallelize, queueIn);

        // Then
        VolatileAccessEvent volatileAccessEvent = (VolatileAccessEvent) eventList.get(0);
        assertThat(volatileAccessEvent.threadIndex(), is(THREAD_INDEX));
        assertThat(volatileAccessEvent.methodCounter(), is(0));

        // When
        eventList.clear();
        ;
        callbackStateImpl.processRuntimeEventFactory(runtimeEventFactoryMock, threadLocalForParallelize, queueIn);

        // Then
        volatileAccessEvent = (VolatileAccessEvent) eventList.get(0);
        // Fixme wrong should be 0 only for method enter/exit 1
        assertThat(volatileAccessEvent.methodCounter(), is(1));

    }


}
