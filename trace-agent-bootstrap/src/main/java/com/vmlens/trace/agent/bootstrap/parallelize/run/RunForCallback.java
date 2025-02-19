package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface RunForCallback {

    RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent,
                                  ThreadLocalWhenInTest threadLocalDataWhenInTest);

    RuntimeEventAndWarnings endAtomicAction(RuntimeEvent runtimeEvent,
                                            ThreadLocalWhenInTest threadLocalDataWhenInTest);

    void startAtomicAction(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    void startAtomicActionWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                        RunnableOrThreadWrapper newThread);
    TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(RunnableOrThreadWrapper newWrapper,
                                                             ThreadLocalForParallelize threadLocalForParallelize);
}
