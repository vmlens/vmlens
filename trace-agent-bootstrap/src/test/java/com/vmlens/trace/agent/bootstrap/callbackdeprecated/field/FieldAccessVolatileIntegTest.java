package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class FieldAccessVolatileIntegTest {

    @Test
    public void testRead() {
        // Given
        Object accessedObject = new Object();

        ThreadLocalDataWhenInTestMap threadLocalDataWhenInTestMap = new ThreadLocalDataWhenInTestMap();
        ActualRunMock actualRunMock = new ActualRunMock(new ActualRunMockStrategyTake());

        RunStateMachine runStateMachine = new RunStateMachineFactoryImpl()
                .createInitial(threadLocalDataWhenInTestMap, actualRunMock);

        Run run = new RunTestAdapter(runStateMachine);
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, 1);
        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(1L);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider =
                new ThreadLocalForParallelizeProviderMock(threadLocalForParallelize);
        List<SerializableEvent> eventList = new LinkedList<>();
        GetOrCreateObjectStateMockMapBased getOrCreateObjectStateMockMapBased
                = new GetOrCreateObjectStateMockMapBased();
        FieldAccessVolatile fieldAccessVolatile = new FieldAccessVolatile(MemoryAccessType.IS_READ,
                getOrCreateObjectStateMockMapBased, new ThreadLocalWhenInTestAdapterImpl(threadLocalForParallelizeProvider,
                new QueueInMock(eventList)));

        // When
        fieldAccessVolatile.field_access_from_generated_method(accessedObject, 10L, 2, 5);

        // Then
        assertThat(actualRunMock.interleaveActions().size(), is(1));
        assertThat(actualRunMock.interleaveActions().get(0), instanceOf(VolatileFieldAccess.class));

        assertThat(eventList.size(), is(1));
        assertThat(eventList.get(0), instanceOf(VolatileAccessEvent.class));
    }
}
