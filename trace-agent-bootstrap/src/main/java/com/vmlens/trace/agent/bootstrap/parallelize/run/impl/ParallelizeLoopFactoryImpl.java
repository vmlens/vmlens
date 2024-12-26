package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategyImpl;

public class ParallelizeLoopFactoryImpl implements ParallelizeLoopFactory {

    private final WaitNotifyStrategy waitNotifyStrategy;


    public ParallelizeLoopFactoryImpl() {
        this(new WaitNotifyStrategyImpl());
    }

    public ParallelizeLoopFactoryImpl(WaitNotifyStrategy waitNotifyStrategy) {
        this.waitNotifyStrategy = waitNotifyStrategy;
    }

    @Override
    public ParallelizeLoop create(int loopId) {
        return new ParallelizeLoop(loopId, new RunStateMachineFactoryImpl(), waitNotifyStrategy, new InterleaveLoop());
    }

}
