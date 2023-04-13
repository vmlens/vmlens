package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.LoopThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.InterleaveActionWithPositionFactoryFactory;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

/**
 * Responsible for creating parallelize actions. Basically the command design pattern.
 * called from external callbacks for example lock.
 */

public class ParallelizeFacade {

    static volatile ParallelizeLoopContainer parallelizeLoopContainer =
            new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl());

    public static void beforeFieldAccessVolatile(ThreadState loopThreadState,
                                                 long objectHashCode, int fieldId, int operation) {
        ParallelizedThreadLocal parallelizedThreadLocal = loopThreadState.getParallelizedThreadLocal();
        if(parallelizedThreadLocal != null) {
            parallelizedThreadLocal.after(new InterleaveActionWithPositionFactoryFactory(
                    new VolatileFieldAccess(fieldId,operation),loopThreadState.threadId()));
        }
    }

    public static boolean beginThreadMethodEnter(LoopThreadState loopThreadState,
                                                 RunnableOrThreadWrapper beganTask) {
        return parallelizeLoopContainer.beginThreadMethodEnter(loopThreadState,beganTask);
    }

    public static boolean hasNext(LoopThreadState loopThreadState, Object obj) {
        return parallelizeLoopContainer.hasNext(loopThreadState,obj);
    }

    public static void close(LoopThreadState loopThreadState, Object obj) {
        parallelizeLoopContainer.close(loopThreadState,obj);
    }

}
