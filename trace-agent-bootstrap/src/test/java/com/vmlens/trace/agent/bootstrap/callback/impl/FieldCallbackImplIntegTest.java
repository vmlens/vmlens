package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.callback.impl.CallbackTestContainer.TEST_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.After.after;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.EndAtomicOperation.endAtomicOperation;
import static com.vmlens.trace.agent.bootstrap.callback.impl.runaction.StartAtomicOperation.startAtomicOperation;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FieldCallbackImplIntegTest {


    @Test
    public void volatileField() {
        // Given
        Object object = new Object();
        int position = 2;
        int inMethodId = 56;
        FieldOwnerAndName volatileField = new FieldOwnerAndName("test.Class", "volatile");
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        int fieldId = callbackTestContainer.fieldIdToStrategy().getIdAndSetFieldIsVolatile(volatileField);

        VolatileFieldAccessEvent volatileAccessEvent = new VolatileFieldAccessEvent();
        volatileAccessEvent.setThreadIndex(TEST_THREAD_INDEX);
        volatileAccessEvent.setMethodId(inMethodId);
        volatileAccessEvent.setOperation(MemoryAccessType.IS_READ);
        volatileAccessEvent.setObjectHashCode(System.identityHashCode(object));

        // When
        callbackTestContainer.fieldCallbackImpl().beforeFieldRead(object, fieldId, position, inMethodId);
        callbackTestContainer.fieldCallbackImpl().afterFieldAccess();

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(volatileAccessEvent));

        assertThat(callbackTestContainer.runActions().size(), is(2));
        assertThat(callbackTestContainer.runActions().get(0), is(startAtomicOperation()));
        assertThat(callbackTestContainer.runActions().get(1), is(endAtomicOperation(volatileAccessEvent)));
    }

    @Test
    public void normalField() {
        // Given
        Object object = new Object();
        int position = 2;
        int inMethodId = 56;
        FieldOwnerAndName field = new FieldOwnerAndName("test.Class", "field");
        CallbackTestContainer callbackTestContainer = CallbackTestContainer.create(true);
        int fieldId = callbackTestContainer.fieldIdToStrategy().getIdAndSetFieldIsNormal(field);

        FieldAccessEvent fieldAccessEvent = new FieldAccessEvent();
        fieldAccessEvent.setThreadIndex(TEST_THREAD_INDEX);
        fieldAccessEvent.setMethodId(inMethodId);
        fieldAccessEvent.setOperation(MemoryAccessType.IS_READ);
        fieldAccessEvent.setObjectHashCode(System.identityHashCode(object));

        // When
        callbackTestContainer.fieldCallbackImpl().beforeFieldRead(object, fieldId, position, inMethodId);
        callbackTestContainer.fieldCallbackImpl().afterFieldAccess();

        // Then
        assertThat(callbackTestContainer.eventList().size(), is(1));
        assertThat(callbackTestContainer.eventList().get(0), is(fieldAccessEvent));

        assertThat(callbackTestContainer.runActions().size(), is(1));
        assertThat(callbackTestContainer.runActions().get(0), is(after(fieldAccessEvent)));
    }
}
