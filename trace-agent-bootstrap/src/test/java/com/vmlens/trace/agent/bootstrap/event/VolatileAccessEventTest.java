package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectState;
import com.vmlens.trace.agent.bootstrap.callback.field.GetOrCreateObjectStateMockMapBased;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.field.RuntimeEventFactoryVolatileField;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
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

        //
        for (int i = 0; i < 5; i++) {
            runtimeEventFactoryVolatileField.create();
        }

        event = runtimeEventFactoryVolatileField.create();

        // Then
        assertThat(event.order(), is(6));
    }

    public void methodCounter() {

    }

    public void createInterleaveAction() {

    }
}
