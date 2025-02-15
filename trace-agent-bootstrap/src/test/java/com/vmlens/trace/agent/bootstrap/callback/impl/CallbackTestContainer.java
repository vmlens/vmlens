package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackTestContainer {

    private final OrderMap<Long> monitorOrder;
    private final List<SerializableEvent> eventList;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final ThreadLocalWhenInTest threadLocalWhenInTest;
    private final RunForCallbackMock run;
    private final MethodCallbackImpl methodCallbackImpl;

    private CallbackTestContainer(OrderMap<Long> monitorOrder,
                                  List<SerializableEvent> eventList,
                                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                                  ThreadLocalWhenInTest threadLocalWhenInTest,
                                  RunForCallbackMock run,
                                  MethodCallbackImpl methodCallbackImpl) {
        this.monitorOrder = monitorOrder;
        this.eventList = eventList;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.threadLocalWhenInTest = threadLocalWhenInTest;
        this.run = run;
        this.methodCallbackImpl = methodCallbackImpl;
    }

    public static CallbackTestContainer create(ThreadLocalWhenInTest threadLocalWhenInTest) {
        List<SerializableEvent> eventList = new LinkedList<>();
        QueueInMock queueInMock = new QueueInMock(eventList);
        RunForCallbackMock run = new RunForCallbackMock();

        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter = createThreadLocalWhenInTestAdapter(threadLocalWhenInTest, queueInMock);

        OrderMap<Long> monitorOrder = new OrderMap<>();
        MethodRepositoryImpl methodRepository = new MethodRepositoryImpl();
        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        return new CallbackTestContainer(monitorOrder, eventList, threadLocalWhenInTestAdapter,
                threadLocalWhenInTest, run, methodCallbackImpl);
    }


    private static ThreadLocalWhenInTestAdapter createThreadLocalWhenInTestAdapter(ThreadLocalWhenInTest threadLocalWhenInTest,
                                                                                   QueueInMock queueInMock) {
        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider =
                mock(ThreadLocalForParallelizeProvider.class);

        ThreadLocalForParallelize threadLocalForParallelizeInside = new ThreadLocalForParallelize(5L, "threadName");
        threadLocalForParallelizeInside.setThreadLocalDataWhenInTest(threadLocalWhenInTest);
        when(threadLocalForParallelizeProvider.threadLocalForParallelize())
                .thenReturn(threadLocalForParallelizeInside);
        return new ThreadLocalWhenInTestAdapterImpl(threadLocalForParallelizeProvider, queueInMock);
    }

    public OrderMap<Long> monitorOrder() {
        return monitorOrder;
    }

    public List<SerializableEvent> eventList() {
        return eventList;
    }

    public ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter() {
        return threadLocalWhenInTestAdapter;
    }

    public ThreadLocalWhenInTest threadLocalWhenInTest() {
        return threadLocalWhenInTest;
    }

    public RunForCallbackMock run() {
        return run;
    }

    public MethodCallbackImpl methodCallbackImpl() {
        return methodCallbackImpl;
    }
}
