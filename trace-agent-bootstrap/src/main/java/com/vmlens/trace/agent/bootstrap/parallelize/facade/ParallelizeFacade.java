package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.InterleaveActionWithPositionFactoryFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopContainer;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

/**
 * Responsible for creating parallelize actions. Basically the command design pattern.
 * called from external callbacks for example lock.
 */

public class ParallelizeFacade {

    static volatile ParallelizeLoopContainer parallelizeLoopContainer =
            new ParallelizeLoopContainer(new ParallelizeLoopFactoryImpl());

    public static void beforeFieldAccessVolatile(ThreadLocalWrapper threadLocalWrapper,
                                                 long objectHashCode, int fieldId, int operation) {
        debugMethodCall(threadLocalWrapper,"beforeFieldAccessVolatile");
        // ToDo must be before call
        new TestThreadState(threadLocalWrapper).after(new InterleaveActionWithPositionFactoryFactory(
                new VolatileFieldAccess(fieldId, operation)));
    }

    public static void beforeThreadStart(ThreadLocalWrapper threadLocalWrapper,
                                         RunnableOrThreadWrapper runnableOrThreadWrapper) {
        debugMethodCall(threadLocalWrapper,"beforeThreadStart");
        // we need to know that a thread was started before the thread calls beginThreadMethodEnter
        // there fore beforeThreadStart
        // that we treat this as after concerning the interleave algo
        // should be no problem
        new TestThreadState(threadLocalWrapper).after(new ThreadStart(runnableOrThreadWrapper));
    }
    public static void beginThreadMethodEnter(ThreadLocalWrapper threadLocalWrapper,
                                                 RunnableOrThreadWrapper beganTask) {
        debugMethodCall(threadLocalWrapper,"beginThreadMethodEnter");
        parallelizeLoopContainer.beginThreadMethodEnter(new TestThreadState(threadLocalWrapper), beganTask);
    }

    public static boolean hasNext(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        debugMethodCall(threadLocalWrapper,"hasNext");
        return parallelizeLoopContainer.hasNext(new TestThreadState(threadLocalWrapper), obj);
    }

    public static void close(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        debugMethodCall(threadLocalWrapper,"close");
        parallelizeLoopContainer.close(new TestThreadState(threadLocalWrapper), obj);
    }

    private static void debugMethodCall(ThreadLocalWrapper threadLocalWrapper, String methodName) {
        agentLogger().debug(threadLocalWrapper.threadId() + ":" +methodName );
    }

    private static AgentLogger agentLogger() {
        return parallelizeLoopContainer.agentLogger();
    }


}
