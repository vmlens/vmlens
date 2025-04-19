package com.vmlens.trace.agent.bootstrap.inttest.util;

import com.vmlens.trace.agent.bootstrap.callback.impl.FieldCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.MethodCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.impl.PreAnalyzedCallbackImpl;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.*;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryImpl;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateMachineFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CallbackTestContainer {

    public static final int LOOP_ID = 3;
    public static final int RUN_ID = 34;

    public static final int TEST_THREAD_INDEX = 1;
    public static final int STARTED_THREAD_INDEX = 3;


    private final List<SerializableEvent> eventList;
    private final WaitNotifyStrategyMock waitNotifyStrategyMock;
    private final MethodCallbackImpl methodCallbackImpl;
    private final PreAnalyzedCallbackImpl preAnalyzedCallbackImpl;
    private final MethodRepositoryImpl methodRepository;
    private final FieldRepositoryImpl fieldIdToStrategy;
    private final FieldCallbackImpl fieldCallbackImpl;
    private final ReadWriteLockMap readWriteLockMap;

    private CallbackTestContainer(List<SerializableEvent> eventList,
                                  WaitNotifyStrategyMock waitNotifyStrategyMock,
                                  MethodCallbackImpl methodCallbackImpl,
                                  PreAnalyzedCallbackImpl preAnalyzedCallbackImpl,
                                  MethodRepositoryImpl methodRepository,
                                  FieldRepositoryImpl fieldIdToStrategy,
                                  FieldCallbackImpl fieldCallbackImpl,
                                  ReadWriteLockMap readWriteLockMap) {
        this.eventList = eventList;
        this.waitNotifyStrategyMock = waitNotifyStrategyMock;
        this.methodCallbackImpl = methodCallbackImpl;
        this.preAnalyzedCallbackImpl = preAnalyzedCallbackImpl;
        this.methodRepository = methodRepository;
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.fieldCallbackImpl = fieldCallbackImpl;
        this.readWriteLockMap = readWriteLockMap;
    }

    public static CallbackTestContainer create() {
        ThreadIndexAndThreadStateMap threadIndexAndThreadStateMap = new ThreadIndexAndThreadStateMap();
        return create(new RunStateMachineFactoryImpl().createInitial(threadIndexAndThreadStateMap));
    }

    public static CallbackTestContainer create(RunStateMachine runStateMachine) {
        List<SerializableEvent> eventList = new LinkedList<>();
        QueueInMock queueInMock = new QueueInMock(eventList);
        WaitNotifyStrategyMock waitNotifyStrategyMock = new WaitNotifyStrategyMock();

        Run run = new RunImpl(new ReentrantLock(),waitNotifyStrategyMock,runStateMachine,LOOP_ID,RUN_ID);


        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(run, TEST_THREAD_INDEX);


        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter = createThreadLocalWhenInTestAdapter(threadLocalWhenInTest, queueInMock);


        MethodRepositoryImpl methodRepository = new MethodRepositoryImpl();

        CheckIsThreadRun checkIsThreadRun = mock(CheckIsThreadRun.class);
        when(checkIsThreadRun.isThreadRun()).thenReturn(true);

        ReadWriteLockMap readWriteLockMap = new  ReadWriteLockMap();

        MethodCallbackImpl methodCallbackImpl = new MethodCallbackImpl(new ParallelizeFacadePassThrough(run),
                methodRepository,
                threadLocalWhenInTestAdapter, checkIsThreadRun,readWriteLockMap);
        PreAnalyzedCallbackImpl preAnalyzedCallbackImpl = new PreAnalyzedCallbackImpl(methodRepository,
                threadLocalWhenInTestAdapter,readWriteLockMap);

        FieldRepositoryImpl fieldRepository = new FieldRepositoryImpl();


        FieldCallbackImpl fieldCallbackImpl = new FieldCallbackImpl(fieldRepository,
                threadLocalWhenInTestAdapter,readWriteLockMap);

        return new CallbackTestContainer(eventList, waitNotifyStrategyMock,
                methodCallbackImpl, preAnalyzedCallbackImpl, methodRepository, fieldRepository, fieldCallbackImpl,readWriteLockMap);
    }

    public static ThreadLocalWhenInTestAdapter createThreadLocalWhenInTestAdapter(ThreadLocalWhenInTest threadLocalWhenInTest,
                                                                                   QueueInMock queueInMock) {
        ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider =
                mock(ThreadLocalForParallelizeProvider.class);

        ThreadLocalForParallelize threadLocalForParallelizeInside = new ThreadLocalForParallelize(new ThreadForParallelizeMock(5L, "threadName"));
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

    public ReadWriteLockMap readWriteLockMap() {
        return readWriteLockMap;
    }
}
