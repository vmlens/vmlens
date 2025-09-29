package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD_POOL;

public class StartNewThreadByPool extends AbstractInTestAction {

    private final Object pool;
    private final Runnable task;
    private final int threadCount;
    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

    public StartNewThreadByPool(Object pool,
                                Runnable task,
                                int threadCount) {
        this.pool = pool;
        this.task = task;
        this.threadCount = threadCount;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        // create new thread (would be good to start it through the test factory)
        Thread thread = new Thread(task, "ThreadPool-"+threadCount);
        thread.setDaemon(true);

        // call threadStartedByPool
        ThreadStartEvent threadStartEvent = new ThreadStartEvent(new ThreadWrapper(thread),THREAD_POOL.code());
        threadStartEvent.setInMethodIdAndPosition(threadLocalDataWhenInTest.inMethodIdAndPosition().inMethodId(),
                threadLocalDataWhenInTest.inMethodIdAndPosition().position());
        ThreadStartedByPoolContext threadStartedByPoolContext =
                new ThreadStartedByPoolContext(pool,task,thread,threadLocalDataWhenInTest,queueIn,threadStartEvent);
        threadLocalDataWhenInTest.runAdapter().threadStartedByPool(threadStartedByPoolContext);

        // start thread with Task
        thread.start();

        // after thread Start
        threadLocalDataWhenInTest.runAdapter().afterLockExitWaitOrThreadStart(threadLocalDataWhenInTest,queueIn);
    }

    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }
}
