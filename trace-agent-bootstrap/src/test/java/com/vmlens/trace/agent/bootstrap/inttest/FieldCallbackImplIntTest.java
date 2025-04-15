package com.vmlens.trace.agent.bootstrap.inttest;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.inttest.util.CallbackTestContainer;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.inttest.util.CallbackTestContainer.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldCallbackImplIntTest {


    @Test
    public void volatileField() {
        // Given
        Object object = new Object();
        int position = 2;
        int inMethodId = 56;
        FieldOwnerAndName volatileField = new FieldOwnerAndName("test.Class", "volatile");
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create();
        int fieldId = callbackTestContainer.fieldIdToStrategy().getIdAndSetFieldIsVolatile(volatileField);

        VolatileFieldAccessEvent volatileAccessEvent = new VolatileFieldAccessEvent(object);
        volatileAccessEvent.setThreadIndex(TEST_THREAD_INDEX);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(MemoryAccessType.IS_READ);
        volatileAccessEvent.setObjectHashCode(System.identityHashCode(object));
        volatileAccessEvent.setBytecodePosition(position);
        volatileAccessEvent.setLoopId(LOOP_ID);
        volatileAccessEvent.setRunId(RUN_ID);

        // When
        callbackTestContainer.fieldCallbackImpl().beforeFieldRead(object, fieldId, position, inMethodId);
        callbackTestContainer.fieldCallbackImpl().afterFieldAccess();

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(volatileAccessEvent));
    }

    @Test
    public void normalField() {
        // Given
        Object object = new Object();
        int position = 2;
        int inMethodId = 56;
        FieldOwnerAndName field = new FieldOwnerAndName("test.Class", "field");
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create();
        int fieldId = callbackTestContainer.fieldIdToStrategy().getIdAndSetFieldIsNormal(field);

        FieldAccessEvent fieldAccessEvent = new FieldAccessEvent();
        fieldAccessEvent.setThreadIndex(TEST_THREAD_INDEX);
        fieldAccessEvent.setMethodId(inMethodId);
        fieldAccessEvent.setOperation(MemoryAccessType.IS_READ);
        fieldAccessEvent.setObjectHashCode(System.identityHashCode(object));
        fieldAccessEvent.setLoopId(LOOP_ID);
        fieldAccessEvent.setRunId(RUN_ID);

        // When
        callbackTestContainer.fieldCallbackImpl().beforeFieldRead(object, fieldId, position, inMethodId);
        callbackTestContainer.fieldCallbackImpl().afterFieldAccess();

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(fieldAccessEvent));
    }
}
