package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents.empty;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final ThreadIndexAndThreadStateMap runContext;
    private final int startedThreadIndex;
    private final RunStateActive nextState;

    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread,
                                    ThreadIndexAndThreadStateMap runContext,
                                    int startedThreadIndex,
                                    RunStateActive nextState) {
        this.startedThread = startedThread;
        this.runContext = runContext;
        this.startedThreadIndex = startedThreadIndex;
        this.nextState = nextState;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return false;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTestAndSerializableEvents> processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                                            ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        if (!startedThread.equals(newWrapper)) {
            return RunStateAndResult.of(this, empty());
        }
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = new TLinkedList<>();
        ThreadLocalWhenInTest threadLocalDataWhenInTest = runContext.createForStartedThread(
                run, threadLocalForParallelize, startedThreadIndex, serializableEvents);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        return RunStateAndResult.of(nextState, new ThreadLocalWhenInTestAndSerializableEvents(
                threadLocalDataWhenInTest, serializableEvents));
    }

    @Override
    public RunState startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunState startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                      RunnableOrThreadWrapper newThread,
                                                      ThreadIndexAndThreadStateMap runContext) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunStateAndResult<RuntimeEvent> after(ProcessRuntimeEventCallback processRuntimeEventCallback, RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return RunStateAndResult.of(this,
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }

    @Override
    public boolean isStartAtomicOperationPossible() {
        return false;
    }

    @Override
    public void checkStopWaiting(ThreadIndexAndThreadStateMap runContext) throws TestBlockedException {

    }
}
