package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;

/**
 * Responsible for creating parallelize actions. Basically the command design pattern.
 */
public class ParallelizeFacadeImpl {
    private final ParallelizeLoopContainer parallelizeLoopContainer;

    public ParallelizeFacadeImpl(ParallelizeLoopContainer parallelizeLoopContainer) {
        this.parallelizeLoopContainer = parallelizeLoopContainer;
    }

    public void after(ThreadLocalWrapper threadLocalWrapper,
                      ParallelizeAction parallelizeAction) {
        new TestThreadState(threadLocalWrapper).after(parallelizeAction);
    }

    public void beforeThreadStart(ThreadLocalWrapper threadLocalWrapper,
                                  RunnableOrThreadWrapper runnableOrThreadWrapper) {
        new TestThreadState(threadLocalWrapper).addCreatedThreadForAfterStart(runnableOrThreadWrapper);
    }


    public void beginThreadMethodEnter(ThreadLocalWrapper threadLocalWrapper,
                                       RunnableOrThreadWrapper beganTask) {
        parallelizeLoopContainer.beginThreadMethodEnter(new TestThreadState(threadLocalWrapper), beganTask);
    }

    public boolean hasNext(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        return parallelizeLoopContainer.hasNext(new TestThreadState(threadLocalWrapper), obj);
    }

    public void close(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        parallelizeLoopContainer.close(new TestThreadState(threadLocalWrapper), obj);
    }



    public void afterThreadStart(ThreadLocalWrapper threadLocalWrapper) {
        new TestThreadState(threadLocalWrapper).afterThreadStart();
    }
}
