package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ParallelizeLoopFactoryImpl;
import org.apache.commons.lang3.tuple.Pair;


/**
 * Fixme synchronization
 */


public class ParallelizeFacade {

    private static volatile ParallelizeFacade parallelizeFacade =
            new ParallelizeFacade(new ParallelizeLoopRepository(new ParallelizeLoopFactoryImpl()));
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private ParallelizeLoop currentLoop;


    public static ParallelizeFacade parallelize() {
        return parallelizeFacade;
    }

    public ParallelizeFacade(ParallelizeLoopRepository parallelizeLoopRepository) {
        this.parallelizeLoopRepository = parallelizeLoopRepository;
    }

    public void beginThreadMethodEnter(ThreadLocalForParallelize threadLocalWrapperForParallelize,
                                       RunnableOrThreadWrapper beganTask) {
        if (currentLoop != null) {
            currentLoop.beginThreadMethodEnter(threadLocalWrapperForParallelize, beganTask);
        }
    }

    public HasNextResult hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        Pair<ParallelizeLoop, Boolean> currentLoopAndNewFlag = parallelizeLoopRepository.getOrCreate(obj);
        currentLoop = currentLoopAndNewFlag.getLeft();

        return currentLoop.hasNext(threadLocalWrapperForParallelize, obj);
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        currentLoop.close(threadLocalWrapperForParallelize);
        currentLoop = null;
    }
}
