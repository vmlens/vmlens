package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.impl.runaction.RunAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackTestContainer {

    public static final int TEST_THREAD_INDEX = 1;

    private final OrderMap<Long> monitorOrder;
    private final List<SerializableEvent> eventList;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final RunForCallbackMock run;
    private final MethodCallbackImpl methodCallbackImpl;
    private final PreAnalyzedCallbackImpl preAnalyzedCallbackImpl;
    private final MethodRepositoryImpl methodRepository;

    private CallbackTestContainer(OrderMap<Long> monitorOrder,
                                  List<SerializableEvent> eventList,
                                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                                  RunForCallbackMock run,
                                  MethodCallbackImpl methodCallbackImpl, PreAnalyzedCallbackImpl preAnalyzedCallbackImpl, MethodRepositoryImpl methodRepository) {
        this.monitorOrder = monitorOrder;
        this.eventList = eventList;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.run = run;
        this.methodCallbackImpl = methodCallbackImpl;
        this.preAnalyzedCallbackImpl = preAnalyzedCallbackImpl;
        this.methodRepository = methodRepository;
    }

    public static CallbackTestContainer create(boolean insideTest) {
        List<SerializableEvent> eventList = new LinkedList<>();
        QueueInMock queueInMock = new QueueInMock(eventList);
        RunForCallbackMock run = new RunForCallbackMock();

        ThreadLocalWhenInTest threadLocalWhenInTest = null;
        if (insideTest) {
            threadLocalWhenInTest = new ThreadLocalWhenInTest(run, TEST_THREAD_INDEX);
        }

        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter = createThreadLocalWhenInTestAdapter(threadLocalWhenInTest, queueInMock);

        OrderMap<Long> monitorOrder = new OrderMap<>();
        MethodRepositoryImpl methodRepository = new MethodRepositoryImpl();

        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter);
        PreAnalyzedCallbackImpl preAnalyzedCallbackImpl = new PreAnalyzedCallbackImpl(methodRepository,
                threadLocalWhenInTestAdapter);

        return new CallbackTestContainer(monitorOrder, eventList, threadLocalWhenInTestAdapter,
                run, methodCallbackImpl, preAnalyzedCallbackImpl, methodRepository);
    }

    public static ThreadLocalWhenInTestAdapter createThreadLocalWhenInTestAdapter(ThreadLocalWhenInTest threadLocalWhenInTest,
                                                                                   QueueInMock queueInMock) {
        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider =
                mock(ThreadLocalForParallelizeProvider.class);

        ThreadLocalForParallelize threadLocalForParallelizeInside = new ThreadLocalForParallelize(5L, "threadName");
        threadLocalForParallelizeInside.setThreadLocalDataWhenInTest(threadLocalWhenInTest);
        when(threadLocalForParallelizeProvider.threadLocalForParallelize())
                .thenReturn(threadLocalForParallelizeInside);
        return new ThreadLocalWhenInTestAdapterImpl(threadLocalForParallelizeProvider, queueInMock);
    }

    public void setStrategyAll(int methodId, StrategyAll strategyAll) {
        methodRepository.setStrategyAll(methodId, strategyAll);
    }

    public void setStrategyPreAnalyzed(int methodId, StrategyPreAnalyzed strategyPreAnalyzed) {
        methodRepository.setStrategyPreAnalyzed(methodId, strategyPreAnalyzed);
    }

    public List<RunAction> runActions() {
        return run.runActions();
    }

    public List<SerializableEvent> eventList() {
        return eventList;
    }

    public MethodCallbackImpl methodCallbackImpl() {
        return methodCallbackImpl;
    }

    public PreAnalyzedCallbackImpl preAnalyzedCallbackImpl() {
        return preAnalyzedCallbackImpl;
    }
}
