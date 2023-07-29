package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.InterleaveActionWithPositionFactoryFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadJoin;
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

    public void afterFieldAccessVolatile(ThreadLocalWrapper threadLocalWrapper,
                                         int fieldId, int operation) {
        debugMethodCall(threadLocalWrapper, "beforeFieldAccessVolatile");
        // ToDo must be before call
        new TestThreadState(threadLocalWrapper).after(new InterleaveActionWithPositionFactoryFactory(
                new VolatileFieldAccess(fieldId, operation)));
    }

    public void beforeThreadStart(ThreadLocalWrapper threadLocalWrapper,
                                  RunnableOrThreadWrapper runnableOrThreadWrapper) {
        debugMethodCall(threadLocalWrapper, "beforeThreadStart");
        new TestThreadState(threadLocalWrapper).addCreatedThreadForAfterStart(runnableOrThreadWrapper);
    }

    public void beforeThreadJoin(ThreadLocalWrapper threadLocalWrapper, long joinedThreadId) {
        debugMethodCall(threadLocalWrapper, "beforeThreadJoin");
        new TestThreadState(threadLocalWrapper).after(
                new ThreadJoin(joinedThreadId));
    }

    public void beginThreadMethodEnter(ThreadLocalWrapper threadLocalWrapper,
                                       RunnableOrThreadWrapper beganTask) {
        debugMethodCall(threadLocalWrapper, "beginThreadMethodEnter");
        parallelizeLoopContainer.beginThreadMethodEnter(new TestThreadState(threadLocalWrapper), beganTask);
    }

    public boolean hasNext(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        debugMethodCall(threadLocalWrapper, "hasNext");
        return parallelizeLoopContainer.hasNext(new TestThreadState(threadLocalWrapper), obj);
    }

    public void close(ThreadLocalWrapper threadLocalWrapper, Object obj) {
        debugMethodCall(threadLocalWrapper, "close");
        parallelizeLoopContainer.close(new TestThreadState(threadLocalWrapper), obj);
    }

    private void debugMethodCall(ThreadLocalWrapper threadLocalWrapper, String methodName) {
        agentLogger().debug(this.getClass(), threadLocalWrapper.threadId() + ":" + methodName);
    }

    private AgentLogger agentLogger() {
        return parallelizeLoopContainer.agentLogger();
    }

    public void afterThreadStart(ThreadLocalWrapper threadLocalWrapper) {
        debugMethodCall(threadLocalWrapper, "afterThreadStart");
        new TestThreadState(threadLocalWrapper).afterThreadStart();
    }
}
