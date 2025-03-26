package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.LogLevelSingleton;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.Warning;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.exception.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class RunImpl implements Run {
    private final ReentrantLock lock;
    private final Condition threadActiveCondition;
    private final WaitNotifyStrategy waitNotifyStrategy;
    private final RunStateMachine runStateMachine;
    private final int loopId;
    private final int runId;

    public RunImpl(ReentrantLock lock, WaitNotifyStrategy waitNotifyStrategy,
                   RunStateMachine runStateMachine, int loopId, int runId) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.runStateMachine = runStateMachine;
        this.lock = lock;
        this.threadActiveCondition = lock.newCondition();
        this.loopId = loopId;
        this.runId = runId;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        RuntimeEventOperation operation = new RuntimeEventOperationAfter();
        fillOperations(operation);
        return operation.execute(runtimeEvent,threadLocalDataWhenInTest);
    }


    public TLinkedList<TLinkableWrapper<SerializableEvent>> endAtomicAction(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        RuntimeEventOperation operation = new RuntimeEventOperationEndAtomicOperation();
        fillOperations(operation);
        return operation.execute(runtimeEvent,threadLocalDataWhenInTest);
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(RunnableOrThreadWrapper newWrapper,
                                                                    ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            ThreadLocalWhenInTestAndSerializableEvents result = runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
            TLinkedList<TLinkableWrapper<SerializableEvent>> results = result.serializableEvents();
            if (result.threadLocalWhenInTest() != null) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(result.threadLocalWhenInTest(), runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                        Warning warning = new LoopWarningEvent(loopId, runId, e.id());
                        results.add(wrap(warning));
                    }
                }
            }
            return result.serializableEvents();
        } finally {
            lock.unlock();
        }
    }

    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        lock.lock();
        try {
            return runStateMachine.end(threadLocalForParallelize);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int runId() {
        return runId;
    }

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> startAtomicAction(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        lock.lock();
        TLinkedList<TLinkableWrapper<SerializableEvent>> results = new  TLinkedList<>();
        try {
            try {
                waitNotifyStrategy.waitForCanStartAtomicOperation(runStateMachine,
                        threadActiveCondition);
            } catch (TestBlockedException e) {
                if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                    Warning warning = new LoopWarningEvent(loopId, runId, e.id());
                    results.add(wrap(warning));
                }
            }
            runStateMachine.startAtomicOperation(threadLocalDataWhenInTest);
            return results;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> startAtomicActionWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, RunnableOrThreadWrapper newThread) {
        lock.lock();
        TLinkedList<TLinkableWrapper<SerializableEvent>> results = new  TLinkedList<>();
        try {
            try {
                waitNotifyStrategy.waitForCanStartAtomicOperation(runStateMachine,
                        threadActiveCondition);
            } catch (TestBlockedException e) {
                if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                    Warning warning = new LoopWarningEvent(loopId, runId, e.id());
                    results.add(wrap(warning));
                }
            }
            runStateMachine.startAtomicOperationWithNewThread(threadLocalDataWhenInTest, newThread);
            return results;
        } finally {
            lock.unlock();
        }
    }


    private void fillOperations(RuntimeEventOperation operation) {
        operation.setRunId(runId);
        operation.setLoopId(loopId);
        operation.setRunStateMachine(runStateMachine);
        operation.setThreadActiveCondition(threadActiveCondition);
        operation.setWaitNotifyStrategy(waitNotifyStrategy);
        operation.setLock(lock);
    }
}
