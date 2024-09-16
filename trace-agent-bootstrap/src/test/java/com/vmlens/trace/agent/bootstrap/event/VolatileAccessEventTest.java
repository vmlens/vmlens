package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.RunMock;
import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectState;
import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectStateMockMapBased;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.field.RuntimeEventFactoryVolatileField;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ActualRunMockStrategyTake;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateActive;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VolatileAccessEventTest {

    @Test
    public void creation() {
        // Given
        Object orig = new Object();
        int fieldId = 22;
        int methodId = 1256;
        int operation = MemoryAccessType.IS_WRITE;
        GetOrCreateObjectState getAndUpdateVolatileObjectState = new GetOrCreateObjectStateMockMapBased();

        RuntimeEventFactoryVolatileField runtimeEventFactoryVolatileField =
                new RuntimeEventFactoryVolatileField(orig, fieldId,
                        methodId, operation, getAndUpdateVolatileObjectState);

        // When
        VolatileAccessEvent event = runtimeEventFactoryVolatileField.create();

        // Then
        assertThat(event.fieldId(), is(fieldId));
        assertThat(event.methodId(), is(methodId));
        assertThat(event.operation(), is(operation));
        assertThat(event.order(), is(0));

        // When
        for (int i = 0; i < 5; i++) {
            runtimeEventFactoryVolatileField.create();
        }

        event = runtimeEventFactoryVolatileField.create();

        // Then
        assertThat(event.order(), is(6));
    }

    @Test
    public void methodCounter() {
        // Given
        VolatileAccessEvent event = new VolatileAccessEvent();
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(new RunMock(), 0);
        threadLocalDataWhenInTest.incrementAndGetMethodCount();

        // When
        for (int i = 0; i < 5; i++) {
            threadLocalDataWhenInTest.incrementAndGetMethodCount();
        }

        SerializableEvent result = threadLocalDataWhenInTest.after(event);

        // Then
        assertThat(result, CoreMatchers.<SerializableEvent>is(event));
        assertThat(event.methodCounter(), is(6));


        // When
        threadLocalDataWhenInTest.after(event);

        // Then
        assertThat(event.methodCounter(), is(6));
    }

    @Test
    public void createInterleaveAction() {
        // Expected
        int FIELD_ID = 5;
        int THREAD_INDEX = 20;
        int OPERATION = MemoryAccessType.IS_WRITE;
        VolatileFieldAccess expectedVolatileFieldAccess = new VolatileFieldAccess(THREAD_INDEX, FIELD_ID, OPERATION);

        // Given
        VolatileAccessEvent event = new VolatileAccessEvent();
        event.setFieldId(FIELD_ID);
        event.setThreadIndex(THREAD_INDEX);
        event.setOperation(OPERATION);

        ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());
        RunStateActive runStateActive = new RunStateActive(actualRunMock,
                null, null);

        // When
        runStateActive.after(event, null);

        // Then
        assertThat(actualRunMock.interleaveActions().size(), is(1));
        VolatileFieldAccess volatileFieldAccess = (VolatileFieldAccess) actualRunMock.interleaveActions().get(0);
        assertThat(volatileFieldAccess, is(expectedVolatileFieldAccess));
    }
}
