package com.vmlens.trace.agent.bootstrap.event.impl;

import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.testfixture.QueueInMock;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VolatileAccessEventTest {

    private static final int LOOP_ID = 3;
    private static final int RUN_ID = 7;
    private static final long THREAD_ID = 12L;


    private static final Object ORIG_OBJECT = new Object();
    private static final Long OFFSET = Long.valueOf(5L);
    private static final int FIELD_ID = 7;
    private static final int METHOD_ID = 55;
    private static final int CALLBACK_ID_VOLATILE_WRITE = 3;

    // Fixme gesamter Test
    @Test
    public void createAddValuesAndSend() {
        // Given
        List<StaticEvent> eventList = new LinkedList<>();
        QueueInMock queue = new QueueInMock(eventList);
        FieldAccessCallbackImpl fieldAccessCallback = new FieldAccessCallbackImplTestFactory().create(queue,
                LOOP_ID,
                RUN_ID,
                THREAD_ID);

        // When
        fieldAccessCallback.
                field_access_from_generated_method(ORIG_OBJECT, OFFSET, FIELD_ID, METHOD_ID, CALLBACK_ID_VOLATILE_WRITE);

        // Then
        assertThat(eventList.size(), is(1));

        // ToDo an welcher Stelle sliding window id setzen?
        // ToDo alle felder testen

        VolatileAccessEvent volatileAccessEvent = (VolatileAccessEvent) eventList.get(0);
        assertThat(volatileAccessEvent.threadId(), is(THREAD_ID));
        assertThat(volatileAccessEvent.loopId(), is(LOOP_ID));
        assertThat(volatileAccessEvent.runId(), is(RUN_ID));
        assertThat(volatileAccessEvent.threadId(), is(THREAD_ID));

        /*
        *
        *     int slidingWindowId() {
        return slidingWindowId;

    int programCounter() {
        return programCounter;
    }
    int order() {
        return order;
    }
    int fieldId() {
        return fieldId;
    }
    int methodCounter() {
        return methodCounter;
    }
    int methodId() {
        return methodId;
    }
    int operation() {
        return operation;
    }
    long objectHashCode() {
        return objectHashCode;
    }
    int runPosition() {
        return runPosition;
    }
*/


    }
}
