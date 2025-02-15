package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.fieldidrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldidrepository.FieldRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FieldCallbackImplIntegTest {

    private OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder;
    private OrderMap<Integer> staticVolatileFieldOrder;
    private List<SerializableEvent> eventList;
    private ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private ThreadLocalWhenInTest threadLocalWhenInTest;
    private Run run;
    private FieldRepositoryImpl fieldRepository;

    @Before
    public void setup() {
        volatileFieldOrder = new OrderMap<>();
        staticVolatileFieldOrder = new OrderMap<>();

        eventList = new LinkedList<>();
        QueueInMock queueInMock = new QueueInMock(eventList);
        run = mock(Run.class);
        when(run.endAtomicOperation(any(), any())).thenAnswer(invocationOnMock ->
                RuntimeEventAndWarnings.of((RuntimeEvent) invocationOnMock.getArguments()[0]));

        threadLocalWhenInTest = new ThreadLocalWhenInTest(run, 1);

        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider =
                mock(ThreadLocalForParallelizeProvider.class);

        ThreadLocalForParallelize threadLocalForParallelize = new ThreadLocalForParallelize(5L, "threadName");
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalWhenInTest);

        when(threadLocalForParallelizeProvider.threadLocalForParallelize())
                .thenReturn(threadLocalForParallelize);
        threadLocalWhenInTestAdapter = new ThreadLocalWhenInTestAdapterImpl(threadLocalForParallelizeProvider,
                queueInMock);

        fieldRepository = new FieldRepositoryImpl();
    }

    @Test
    public void volatileField() {
        FieldOwnerAndName volatileField = new FieldOwnerAndName("test.Class", "volatile");
        fieldRepository.getIdAndSetFieldIsVolatile(volatileField);
        int fieldId = fieldRepository.asInt(volatileField);
        accessField(fieldId);
        assertThat(eventList.get(0), instanceOf(VolatileAccessEvent.class));
    }

    private void accessField(int fieldId) {
        Object fromObject = new Object();
        int position = 2;
        int inMethodId = 22;
        FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(fieldRepository,
                volatileFieldOrder,
                staticVolatileFieldOrder,
                threadLocalWhenInTestAdapter);
        fieldCallbackImpl.beforeFieldRead(fromObject, fieldId, position, inMethodId);
        fieldCallbackImpl.afterFieldAccess();
    }
}
