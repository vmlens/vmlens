package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.LogLevelSingleton;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.Warning;
import com.vmlens.trace.agent.bootstrap.exception.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

/**
 *
 * Template method design pattern
 *
 */

public abstract class RuntimeEventOperation {

    private Lock lock;
    private int loopId;
    private int runId;
    private RunStateMachine runStateMachine;
    private WaitNotifyStrategy waitNotifyStrategy;
    private Condition threadActiveCondition;;


    public void setLock(Lock lock) {
        this.lock = lock;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public void setRunStateMachine(RunStateMachine runStateMachine) {
        this.runStateMachine = runStateMachine;
    }

    public void setWaitNotifyStrategy(WaitNotifyStrategy waitNotifyStrategy) {
        this.waitNotifyStrategy = waitNotifyStrategy;
    }

    public void setThreadActiveCondition(Condition threadActiveCondition) {
        this.threadActiveCondition = threadActiveCondition;
    }



    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        lock.lock();
        try {
            TLinkedList<TLinkableWrapper<SerializableEvent>> results = new  TLinkedList<>();
            runtimeEvent.setLoopId(loopId);
            runtimeEvent.setRunId(runId);
            RuntimeEvent result = operation(runStateMachine, runtimeEvent, threadLocalDataWhenInTest);
            results.add(wrap(result));
            if (result.isInterleaveActionFactory()) {
                try {
                    waitNotifyStrategy.notifyAndWaitTillActive(threadLocalDataWhenInTest, runStateMachine, threadActiveCondition);
                } catch (TestBlockedException e) {
                    if (LogLevelSingleton.logLevel().isInfoEnabled()) {
                        Warning warning = new LoopWarningEvent(loopId, runId, e.id());
                        results.add(wrap(warning));
                    }
                }
            }
            return results;
        } finally {
            lock.unlock();
        }
    }

    protected abstract RuntimeEvent operation(RunStateMachine runStateMachine,
                                              RuntimeEvent runtimeEvent,
                                              ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
