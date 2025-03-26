package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.singleton;


public class RunTestAdapter implements Run {

    private final RunStateMachine runStateMachine;

    public RunTestAdapter(RunStateMachine runStateMachine) {
        this.runStateMachine = runStateMachine;
    }

    @Override
    public   TLinkedList<TLinkableWrapper<SerializableEvent>> after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return singleton(runStateMachine.after(runtimeEvent, threadLocalDataWhenInTest));
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> endAtomicAction(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return null;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this)
                .serializableEvents();
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.end(threadLocalForParallelize);
    }

    @Override
    public int runId() {
        return 0;
    }

    @Override
    public int loopId() {
        return 0;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> startAtomicAction(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return null;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> startAtomicActionWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, RunnableOrThreadWrapper newThread) {
        return null;
    }
}
