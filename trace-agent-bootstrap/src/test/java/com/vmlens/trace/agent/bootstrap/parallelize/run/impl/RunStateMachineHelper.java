package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RunStateMachineHelper {

    private final RunStateMachineImpl runStateMachineImpl;
    private final ThreadIndexAndThreadStateMap runContext;
    private final List<SerializableEvent> eventList = new LinkedList<>();
    private final QueueInMock queueInMock = new QueueInMock(eventList);
    private final Run run = mock(Run.class);
    private final ThreadLocalWhenInTest mainThread;

    public RunStateMachineHelper(InterleaveRun interleaveRun) {
        this.runContext = new ThreadIndexAndThreadStateMap();
        ThreadLocalForParallelize threadLocalForParallelize =
                createThreadLocalForParallelize(ThreadState.ACTIVE);
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = new TLinkedList<TLinkableWrapper<SerializableEvent>>();
        mainThread = runContext.createForMainTestThread(run,threadLocalForParallelize,serializableEvents);

        this.runStateMachineImpl  = new RunStateMachineImpl(new RunStateContext(this.runContext,interleaveRun));
    }

    public static ThreadLocalWhenInTestForParallelize threadIndex(int threadIndex) {
        return mock(ThreadLocalWhenInTestForParallelize.class);
    }

    public static ThreadLocalForParallelize createThreadLocalForParallelize(ThreadState threadState) {
        ThreadForParallelize threadForParallelize = mock(ThreadForParallelize.class);
        when(threadForParallelize.isBlocked()).thenReturn(threadState);
        return new ThreadLocalForParallelize(threadForParallelize);
    }

    public static ThreadWrapper createThreadWrapper() {
        return new ThreadWrapper(new Object());
    }

    public SendEvent sendEvent() {
        return mock(SendEvent.class);
    }

    public RunStateMachineImpl runStateMachineImpl() {
        return runStateMachineImpl;
    }

    public Run run() {
        return run;
    }

    public QueueInMock queueInMock() {
        return queueInMock;
    }

    public ThreadLocalWhenInTest mainThread() {
        return mainThread;
    }

    public ThreadLocalWhenInTest startThread(ThreadState threadState) {
        ThreadWrapper threadWrapper = createThreadWrapper();
        runStateMachineImpl().newTestTaskStarted(threadWrapper);
        ThreadLocalForParallelize threadLocalForParallelize = createThreadLocalForParallelize(threadState);
        NewTaskContext newTaskContext = new NewTaskContext(queueInMock(),threadWrapper,threadLocalForParallelize);
        return runStateMachineImpl().processNewTestTask(newTaskContext, run(),sendEvent());
    }

    public ThreadLocalWhenInTest startThread(ThreadLocalForParallelize threadLocalForParallelize) {
        ThreadWrapper threadWrapper = createThreadWrapper();
        runStateMachineImpl().newTestTaskStarted(threadWrapper);
        NewTaskContext newTaskContext = new NewTaskContext(queueInMock(),threadWrapper,threadLocalForParallelize);
        return runStateMachineImpl().processNewTestTask(newTaskContext, run(),sendEvent());
    }
}
