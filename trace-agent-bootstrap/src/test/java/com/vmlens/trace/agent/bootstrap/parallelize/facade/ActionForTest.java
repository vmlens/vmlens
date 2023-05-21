package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeFacadeImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;

/**
 * calls ParallelizeFacade therefore abstract
 */
public abstract class ActionForTest implements WithThreadIndex  {
    private final Position position;

    public ActionForTest(Position position) {
        this.position = position;
    }

    @Override
    public int threadIndex() {
        return position.threadIndex;
    }

    public Position position() {
        return position;
    }

    public abstract void execute(ParallelizeFacadeImpl parallelizeFacadeImpl,
                                 ThreadLocalWrapperMock[] loopThreadStateArray);

}
