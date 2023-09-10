package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeFacadeImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;


/**
 * Singelton for ParallelizeFacadeImpl
 */
public class ParallelizeFacade {

    private static volatile ParallelizeFacadeImpl parallelizeFacadeImpl =
            new ParallelizeFacadeImpl(new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl()));

    private static final ParallelizeActionFactory parallelizeActionFactory = new ParallelizeActionFactory();

    public static ParallelizeActionFactory parallelizeActionFactory() {
        return parallelizeActionFactory;
    }

    public static void after(ThreadLocalWrapper threadLocalWrapper, ParallelizeAction parallelizeAction) {
        parallelizeFacadeImpl.after(threadLocalWrapper, parallelizeAction);
    }

    public static void beforeThreadStart(ThreadLocalWrapper threadLocalWrapper, RunnableOrThreadWrapper runnableOrThreadWrapper) {
        parallelizeFacadeImpl.beforeThreadStart(threadLocalWrapper, runnableOrThreadWrapper);
    }

    public static void beginThreadMethodEnter(ThreadLocalWrapper threadLocalWrapper, RunnableOrThreadWrapper beganTask) {
        parallelizeFacadeImpl.beginThreadMethodEnter(threadLocalWrapper, beganTask);
    }

    public static boolean hasNext(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        return parallelizeFacadeImpl.hasNext(threadLocalWrapper, obj);
    }

    public static void close(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        parallelizeFacadeImpl.close(threadLocalWrapper, obj);
    }

    public static void afterThreadStart(ThreadLocalWrapper threadLocalWrapper) {
        parallelizeFacadeImpl.afterThreadStart(threadLocalWrapper);
    }
}
