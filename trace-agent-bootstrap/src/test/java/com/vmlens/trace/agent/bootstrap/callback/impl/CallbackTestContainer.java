package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.impl.runaction.RunAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackTestContainer {

    public static final int TEST_THREAD_INDEX = 1;
    public static final int STARTED_THREAD_INDEX = 3;


    private final List<SerializableEvent> eventList;
    private final RunForCallbackMock run;
    private final MethodCallbackImpl methodCallbackImpl;
    private final PreAnalyzedCallbackImpl preAnalyzedCallbackImpl;
    private final MethodRepositoryImpl methodRepository;
    private final FieldRepositoryImpl fieldIdToStrategy;

    private final FieldCallbackImpl fieldCallbackImpl;

    private CallbackTestContainer(List<SerializableEvent> eventList,
                                  RunForCallbackMock run,
                                  MethodCallbackImpl methodCallbackImpl,
                                  PreAnalyzedCallbackImpl preAnalyzedCallbackImpl,
                                  MethodRepositoryImpl methodRepository,
                                  FieldRepositoryImpl fieldIdToStrategy,
                                  FieldCallbackImpl fieldCallbackImpl) {
        this.eventList = eventList;
        this.run = run;
        this.methodCallbackImpl = methodCallbackImpl;
        this.preAnalyzedCallbackImpl = preAnalyzedCallbackImpl;
        this.methodRepository = methodRepository;
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.fieldCallbackImpl = fieldCallbackImpl;
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

        CheckIsThreadRun checkIsThreadRun = mock(CheckIsThreadRun.class);
        when(checkIsThreadRun.isThreadRun()).thenReturn(true);

        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(new ParallelizeFacadePassThrough(run),
                methodRepository,
                monitorOrder,
                threadLocalWhenInTestAdapter, checkIsThreadRun);
        PreAnalyzedCallbackImpl preAnalyzedCallbackImpl = new PreAnalyzedCallbackImpl(methodRepository,
                threadLocalWhenInTestAdapter);

        FieldRepositoryImpl fieldRepository = new FieldRepositoryImpl();

        OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder = new OrderMap<>();
        OrderMap<Integer> staticVolatileFieldOrder = new OrderMap<>();
        FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(fieldRepository,
                volatileFieldOrder,
                staticVolatileFieldOrder,
                threadLocalWhenInTestAdapter);

        return new CallbackTestContainer(eventList, run,
                methodCallbackImpl, preAnalyzedCallbackImpl, methodRepository, fieldRepository, fieldCallbackImpl);
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

    public FieldRepositoryImpl fieldIdToStrategy() {
        return fieldIdToStrategy;
    }

    public FieldCallbackImpl fieldCallbackImpl() {
        return fieldCallbackImpl;
    }
}
