package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD;
import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD_POOL;

public class StartNewThreadByPool implements CallbackAction {

    private final Object pool;
    private final Runnable task;
    private final int threadCount;


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
        Thread thread = new Thread(task, "VMLensThreadPool-"+threadCount);
        thread.setDaemon(true);

        // call threadStartedByPool
        ThreadStartedByPoolContext threadStartedByPoolContext =
                new ThreadStartedByPoolContext(pool,task,thread);
        threadLocalDataWhenInTest.runAdapter().threadStartedByPool(threadStartedByPoolContext);

        // start thread with Task
        thread.start();

        // process new Thread Started event
        ThreadStartEvent threadStartEvent = new ThreadStartEvent();
        threadStartEvent.setEventType(THREAD_POOL.code());
        threadStartEvent.setInMethodIdAndPosition(threadLocalDataWhenInTest.inMethodIdAndPosition().inMethodId(),
                threadLocalDataWhenInTest.inMethodIdAndPosition().position());

        AfterContext afterContext = new AfterContext(threadLocalDataWhenInTest,threadStartEvent,queueIn);
        threadLocalDataWhenInTest.runAdapter().after(afterContext);
    }
}
