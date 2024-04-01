package com.vmlens.trace.agent.bootstrap.ucTest;

import com.vmlens.trace.agent.bootstrap.callback.field.FieldAccessCallbackImpl;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.testFixture.QueueInMock;
import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class VolatileAccessEventUcTest {

    private static final Object ORIG_OBJECT = new Object();
    private static final Long OFFSET = Long.valueOf(5L);
    private static final int FIELD_ID = 7;
    private static final int METHOD_ID = 55;
    private static final int CALLBACK_ID_VOLATILE_WRITE = 3;

    // Fixme gesamter Test
    @Test
    public void createAddValuesAndSend() {
        // Expected
        VolatileAccessEvent expectedEvent = new VolatileAccessEvent();

        // Given
        List<StaticEvent> eventList = new LinkedList<>();
        QueueInMock queue = new QueueInMock(eventList);
        FieldAccessCallbackImpl fieldAccessCallback = new FieldAccessCallbackImplTestFactory().create(queue);

        // When
        fieldAccessCallback.
                field_access_from_generated_method(ORIG_OBJECT, OFFSET, FIELD_ID, METHOD_ID, CALLBACK_ID_VOLATILE_WRITE);

        // Then
        assertThat(eventList.size(), is(1));
        assertThat(eventList.get(0), CoreMatchers.<StaticEvent>is(expectedEvent));

    }
}
