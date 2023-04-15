package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalState;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

import java.util.Iterator;

public class ParallelizeLoop {

    private final int loopId;
    private final RunFactory runFactory;
    private final Iterator<CalculatedRun> interleaveLoopIterator;
    private Run currentRun;
    private int maxRunId;

    public ParallelizeLoop(int loopId, RunFactory runFactory, Iterator<CalculatedRun> interleaveLoopIterator) {
        this.loopId = loopId;
        this.runFactory = runFactory;
        this.interleaveLoopIterator = interleaveLoopIterator;
    }

    public boolean beginThreadMethodEnter(ThreadLocalState threadLocalState, RunnableOrThreadWrapper beganTask) {
        return false;
    }

    public boolean hasNext(ThreadLocalState threadLocalState) {
        if (currentRun != null) {
            currentRun.end(threadLocalState);
        }
        if (interleaveLoopIterator.hasNext()) {
            currentRun = runFactory.create(maxRunId, interleaveLoopIterator.next());
            threadLocalState.createNewParallelizedThreadLocal(currentRun);
            return true;
        }
        return false;
    }

    public void close(ThreadLocalState threadLocalState) {
        threadLocalState.setParallelizedThreadLocalToNull();
    }
}
