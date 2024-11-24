package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class CallAfterForRuntimeEventTest {

    @Test
    public void afterFiltered() {
        // Expected
        int FIELD_ID = 5;
        int OPERATION = 1;
        int THREAD_INDEX = 10;
        InterleaveAction expectedInterleaveAction = new VolatileFieldAccess(THREAD_INDEX, FIELD_ID, OPERATION);

        // Given
        VolatileAccessEvent runtimeEvent = new VolatileAccessEvent();
        runtimeEvent.setThreadIndex(THREAD_INDEX);
        runtimeEvent.setOperation(OPERATION);
        runtimeEvent.setFieldId(FIELD_ID);

        ActualRunMock actualRun = new ActualRunMock(new ActualRunMockStrategyFiltered());
        CallAfterForRuntimeEvent callAfterForRuntimeEvent = new CallAfterForRuntimeEvent();

        // When
        RuntimeEvent result = callAfterForRuntimeEvent.after(runtimeEvent, actualRun);

        // Then
        assertThat(result, is(nullValue()));
        InterleaveAction volatileFieldAccess = actualRun.interleaveActions().get(0);
        assertThat(volatileFieldAccess, is(expectedInterleaveAction));

    }

    @Test
    public void after() {
        // Expected
        int FIRST_FIELD_ID = 5;
        int SECOND_FIELD_ID = 44;
        // Given
        VolatileAccessEvent runtimeEvent = new VolatileAccessEvent();
        runtimeEvent.setFieldId(FIRST_FIELD_ID);

        ActualRunMockStrategyTake actualRunMockStrategyTake = new ActualRunMockStrategyTake();
        ActualRunMock actualRun = new ActualRunMock(actualRunMockStrategyTake);
        CallAfterForRuntimeEvent callAfterForRuntimeEvent = new CallAfterForRuntimeEvent();

        // When
        RuntimeEvent result = callAfterForRuntimeEvent.after(runtimeEvent, actualRun);

        // Then
        VolatileAccessEvent resultVolatileAccessEvent = (VolatileAccessEvent) result;
        assertThat(resultVolatileAccessEvent.runPosition(), is(0));
        assertThat(resultVolatileAccessEvent.fieldId(), is(FIRST_FIELD_ID));

        // Given
        runtimeEvent = new VolatileAccessEvent();
        runtimeEvent.setFieldId(SECOND_FIELD_ID);

        result = callAfterForRuntimeEvent.after(runtimeEvent, actualRun);

        // Then
        resultVolatileAccessEvent = (VolatileAccessEvent) result;
        assertThat(resultVolatileAccessEvent.runPosition(), is(1));
        assertThat(resultVolatileAccessEvent.fieldId(), is(SECOND_FIELD_ID));


    }

}
