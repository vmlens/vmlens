package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;


/**
 * Decorator for the specific class @see com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository
 */

public class ParallelizeLoopContainer {
    private final Object lock = new Object();
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private ParallelizeLoop currentLoop;

    public ParallelizeLoopContainer(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopRepository = new ParallelizeLoopRepository(parallelizeLoopFactory);
    }

    public void beginThreadMethodEnter(ThreadLocalForParallelize testThreadState,
                                       RunnableOrThreadWrapper beganTask) {
        synchronized (lock) {
            if (currentLoop != null) {
                currentLoop.beginThreadMethodEnter(testThreadState, beganTask);
            }
        }
    }

    public boolean hasNext(ThreadLocalForParallelize threadLocalForParallelize, Object obj) {
        // ToDo if this is a new loop terminate the old one? or create a warning
        synchronized (lock) {
            currentLoop = parallelizeLoopRepository.getOrCreate(obj);
            return currentLoop.hasNext(threadLocalForParallelize);
        }

    }

    public void close(ThreadLocalForParallelize threadLocalForParallelize, Object obj) {
        // ToDo what to do when this is a different loop than the current?
        synchronized (lock) {
            currentLoop.close(threadLocalForParallelize);
            currentLoop = null;
        }
    }


}