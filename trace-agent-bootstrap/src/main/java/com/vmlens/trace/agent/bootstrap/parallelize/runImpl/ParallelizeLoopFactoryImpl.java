package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopFactory;

public class ParallelizeLoopFactoryImpl implements ParallelizeLoopFactory {
    @Override
    public ParallelizeLoop create(int loopId) {
        return new ParallelizeLoop(loopId, new RunFactoryImpl(), new InterleaveLoop().iterator());
    }
}
