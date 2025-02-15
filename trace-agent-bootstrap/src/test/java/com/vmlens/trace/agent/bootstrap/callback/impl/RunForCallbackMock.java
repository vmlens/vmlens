package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.impl.runaction.*;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;

public class RunForCallbackMock implements RunForCallback {

    private final java.util.List<RunAction> runActions = new LinkedList<>();

    @Override
    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent,
                                         ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        runActions.add(new After(runtimeEvent));
        return RuntimeEventAndWarnings.of(runtimeEvent);
    }

    @Override
    public RuntimeEventAndWarnings endAtomicOperation(RuntimeEvent runtimeEvent,
                                                      ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        runActions.add(new EndAtomicOperation(runtimeEvent));
        return RuntimeEventAndWarnings.of(runtimeEvent);
    }

    @Override
    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        runActions.add(new StartAtomicOperation());
    }

    @Override
    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                  RunnableOrThreadWrapper newThread) {
        runActions.add(new StartAtomicOperationWithNewThread(newThread));
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(RunnableOrThreadWrapper newWrapper,
                                                                    ThreadLocalForParallelize threadLocalForParallelize) {
        runActions.add(new NewTask(newWrapper));
        return emptyList();
    }

    public List<RunAction> runActions() {
        return runActions;
    }
}
