package com.vmlens.trace.agent.bootstrap.callback.field;


import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType.IS_WRITE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class FieldAccessCallbackImplTest {


    private static final Object ORIG_OBJECT = new Object();
    private static final int FIELD_ID = 7;
    private static final int FIRST_METHOD_ID = 55;
    private static final int SECOND_METHOD_ID = 77;

    @Test
    public void testVolatileWrite() {
        // Given
        int CALLBACK_ID_VOLATILE_WRITE = 3;
        ParallelizeBridgeForCallbackMock parallelizeBridgeForCallback = new ParallelizeBridgeForCallbackMock();
        GetOrCreateObjectStateMockMapBased getOrCreateObjectStateMockMapBased = new GetOrCreateObjectStateMockMapBased();
        FieldAccessCallbackImpl fieldAccessCallbackImpl = new FieldAccessCallbackImpl
                (getOrCreateObjectStateMockMapBased, parallelizeBridgeForCallback);

        // When
        fieldAccessCallbackImpl.field_access_from_generated_method(ORIG_OBJECT, null, FIELD_ID, FIRST_METHOD_ID, CALLBACK_ID_VOLATILE_WRITE);
        RuntimeEvent runtimeEvent = parallelizeBridgeForCallback.runtimeEventFactory().create();

        // Then
        VolatileAccessEvent volatileAccessEvent = (VolatileAccessEvent) runtimeEvent;
        assertThat(volatileAccessEvent.fieldId(), is(FIELD_ID));
        assertThat(volatileAccessEvent.methodId(), is(FIRST_METHOD_ID));
        assertThat(volatileAccessEvent.operation(), is(IS_WRITE));
        assertThat(volatileAccessEvent.order(), is(0));
        assertThat(volatileAccessEvent.objectHashCode(), not(0L));

        // When
        fieldAccessCallbackImpl.field_access_from_generated_method(ORIG_OBJECT, null, FIELD_ID, SECOND_METHOD_ID,
                CALLBACK_ID_VOLATILE_WRITE);
        runtimeEvent = parallelizeBridgeForCallback.runtimeEventFactory().create();

        // Then
        volatileAccessEvent = (VolatileAccessEvent) runtimeEvent;
        assertThat(volatileAccessEvent.methodId(), is(SECOND_METHOD_ID));
        assertThat(volatileAccessEvent.order(), is(1));
    }

    public void testNormalRead() {

    }


}
