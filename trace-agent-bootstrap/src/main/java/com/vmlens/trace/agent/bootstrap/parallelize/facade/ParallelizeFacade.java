package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapperForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;


/**
 * Singelton for ParallelizeFacadeImpl
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

    public void after(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize,
                      ParallelizeAction parallelizeAction) {
        new TestThreadState(threadLocalWrapperForParallelize).after(parallelizeAction);
    }

    public void beforeThreadStart(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize,
                                  RunnableOrThreadWrapper runnableOrThreadWrapper) {
        new TestThreadState(threadLocalWrapperForParallelize).addCreatedThreadForAfterStart(runnableOrThreadWrapper);
    }

    public void beginThreadMethodEnter(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize,
                                       RunnableOrThreadWrapper beganTask) {
        parallelizeLoopContainer.beginThreadMethodEnter(new TestThreadState(threadLocalWrapperForParallelize), beganTask);
    }

    public boolean hasNext(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize, Object obj) {
        return parallelizeLoopContainer.hasNext(new TestThreadState(threadLocalWrapperForParallelize), obj);
    }

    public void close(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize, Object obj) {
        parallelizeLoopContainer.close(new TestThreadState(threadLocalWrapperForParallelize), obj);
    }

    public void afterThreadStart(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize) {
        new TestThreadState(threadLocalWrapperForParallelize).afterThreadStart();
    }
}
