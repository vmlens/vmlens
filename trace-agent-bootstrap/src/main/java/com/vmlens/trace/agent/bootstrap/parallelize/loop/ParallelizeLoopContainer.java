package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ParallelizeLoopContainer {
    private final Object lock = new Object();
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private ParallelizeLoop currentLoop;

    public ParallelizeLoopContainer(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopRepository = new ParallelizeLoopRepository(parallelizeLoopFactory);
    }

    public void beginThreadMethodEnter(TestThreadState testThreadState,
                                          RunnableOrThreadWrapper beganTask) {
        synchronized (lock) {
            if (currentLoop != null) {
                currentLoop.beginThreadMethodEnter(testThreadState, beganTask);
            }
        }
    }

    public boolean hasNext(TestThreadState testThreadState, Object obj) {
        // ToDo if this is a new loop terminate the old one? or create a warning
        synchronized (lock) {
            currentLoop = parallelizeLoopRepository.getOrCreate(obj);
            return currentLoop.hasNext(testThreadState);
        }

    }

    public void close(TestThreadState testThreadState, Object obj) {
        // ToDo what to do when this is a different loop than the current?
        synchronized (lock) {
            currentLoop.close(testThreadState);
            currentLoop = null;
        }
    }

    public AgentLogger agentLogger() {
        return parallelizeLoopRepository.agentLogger();
    }

    // For Test
    public Run currentRun() {
        return currentLoop.currentRun();
    }
}