package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepository;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.CheckIsThreadRun;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MethodCallbackImplIntegTest {

    private OrderMap<Long> monitorOrder;
    private List<SerializableEvent> eventList;
    private ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private ThreadLocalWhenInTest threadLocalWhenInTest;
    private Run run;


    @Before
    public void setup() {
        monitorOrder = new OrderMap<>();

        eventList = new LinkedList<>();
        QueueInMock queueInMock = new QueueInMock(eventList);
        run = mock(Run.class);
        when(run.after(any(), any())).thenAnswer(invocationOnMock ->
                RuntimeEventAndWarnings.of((RuntimeEvent) invocationOnMock.getArguments()[0]));
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

    }

    @Test
    public void normalMethodEnter() {
        // Given
        Object object = new Object();
        MethodCallId normalMethod = new MethodCallId("java.test.Test", "normal", "()V");
        MethodRepository methodRepository = new MethodRepository();
        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        int methodId = methodRepository.asInt(normalMethod);

        // When
        methodCallbackImpl.methodEnter(object, methodId);

        // Then
        assertThat(eventList.get(0), instanceOf(MethodEnterEvent.class));
    }

    @Test
    public void normalMethodExit() {
        // Given
        Object object = new Object();
        MethodCallId normalMethod = new MethodCallId("java.test.Test", "normal", "()V");
        MethodRepository methodRepository = new MethodRepository();
        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        int methodId = methodRepository.asInt(normalMethod);

        // When
        methodCallbackImpl.methodExit(object, methodId);

        // Then
        assertThat(eventList.get(0), instanceOf(MethodExitEvent.class));
    }

    @Test
    public void threadStart() {
        // Given
        int position = 3;
        int inMethodId = 7;

        Object thread = new Thread();
        MethodCallId normalMethod = new MethodCallId("does.not.matter", "start", "()V");
        MethodRepository methodRepository = new MethodRepository();
        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        int methodId = methodRepository.asInt(normalMethod);

        // When
        methodCallbackImpl.targetOfMethodCall(thread, methodId);
        methodCallbackImpl.beforeMethodCall(methodId);
        methodCallbackImpl.afterMethodCall(inMethodId, position, methodId);

        // Then
        assertThat(eventList.get(0), instanceOf(ThreadStartEvent.class));
    }

    @Test
    public void threadBegin() {
        // Given
        Object object = new Object();
        MethodCallId threadBegin = new MethodCallId("does.not.matter", "run", "()V");

        CheckIsThreadRun checkIsThreadRun = mock(CheckIsThreadRun.class);
        when(checkIsThreadRun.isThreadRun()).thenReturn(true);

        ParallelizeFacade parallelizeFacade = mock(ParallelizeFacade.class);
        when(parallelizeFacade.beginThreadMethodEnter(any(), any())).thenReturn(new TLinkedList<>());

        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider = mock(ThreadLocalForParallelizeProvider.class);

        MethodRepository methodRepository = new MethodRepository(checkIsThreadRun,
                threadLocalForParallelizeProvider, parallelizeFacade);
        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        int methodId = methodRepository.asInt(threadBegin);

        // When
        methodCallbackImpl.methodEnter(object, methodId);

        // Then
        assertThat(eventList.get(0), instanceOf(MethodEnterEvent.class));
        verify(parallelizeFacade).beginThreadMethodEnter(any(), any());
    }
}
