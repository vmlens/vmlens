package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.InterleaveActionWithPositionFactoryFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalState;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

/**
 * Responsible for creating parallelize actions. Basically the command design pattern.
 * called from external callbacks for example lock.
 */

public class ParallelizeFacade {

    static volatile ParallelizeLoopContainer parallelizeLoopContainer =
            new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl());

    public static void beforeFieldAccessVolatile(ThreadLocalStateForFacade loopThreadState,
                                                 long objectHashCode, int fieldId, int operation) {
        ParallelizedThreadLocal parallelizedThreadLocal = loopThreadState.getParallelizedThreadLocal();
        if (parallelizedThreadLocal != null) {
            parallelizedThreadLocal.after(new InterleaveActionWithPositionFactoryFactory(
                    new VolatileFieldAccess(fieldId, operation), loopThreadState.threadId()));
        }
    }

    public static boolean beginThreadMethodEnter(ThreadLocalState threadLocalState,
                                                 RunnableOrThreadWrapper beganTask) {
        return parallelizeLoopContainer.beginThreadMethodEnter(threadLocalState, beganTask);
    }

    public static boolean hasNext(ThreadLocalState threadLocalState, Object obj) {
        return parallelizeLoopContainer.hasNext(threadLocalState, obj);
    }

    public static void close(ThreadLocalState threadLocalState, Object obj) {
        parallelizeLoopContainer.close(threadLocalState, obj);
    }

}
