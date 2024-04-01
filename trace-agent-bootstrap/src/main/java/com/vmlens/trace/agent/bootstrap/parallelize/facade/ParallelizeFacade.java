package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;


/**
 *
 */


public class ParallelizeFacade {

    private static volatile ParallelizeFacade parallelizeFacade =
            new ParallelizeFacade(new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl()));


    public static ParallelizeFacade parallelize() {
        return parallelizeFacade;
    }

    private final ParallelizeLoopContainer parallelizeLoopContainer;

    public ParallelizeFacade(ParallelizeLoopContainer parallelizeLoopContainer) {
        this.parallelizeLoopContainer = parallelizeLoopContainer;
    }


    public void beginThreadMethodEnter(ThreadLocalForParallelize threadLocalWrapperForParallelize,
                                       RunnableOrThreadWrapper beganTask) {
        //  parallelizeLoopContainer.beginThreadMethodEnter(new TestThreadState(threadLocalWrapperForParallelize), beganTask);
    }

    public boolean hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        // Fixme
        return false; // parallelizeLoopContainer.hasNext(new TestThreadState(threadLocalWrapperForParallelize), obj);
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        // Fixme
        //parallelizeLoopContainer.close(new TestThreadState(threadLocalWrapperForParallelize), obj);
    }


}
