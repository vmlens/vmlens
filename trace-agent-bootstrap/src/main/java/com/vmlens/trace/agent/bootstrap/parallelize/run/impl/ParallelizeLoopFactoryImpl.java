package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.AgentLoggerNoOp;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategy;
import com.vmlens.trace.agent.bootstrap.parallelize.run.WaitNotifyStrategyImpl;

public class ParallelizeLoopFactoryImpl implements ParallelizeLoopFactory {

    private final WaitNotifyStrategy waitNotifyStrategy;
    private final AgentLogger agentLogger;

    public ParallelizeLoopFactoryImpl() {
        this(new WaitNotifyStrategyImpl(new AgentLoggerNoOp()), new AgentLoggerNoOp());
    }

    public ParallelizeLoopFactoryImpl(WaitNotifyStrategy waitNotifyStrategy, AgentLogger agentLogger) {
        this.waitNotifyStrategy = waitNotifyStrategy;
        this.agentLogger = agentLogger;
    }

    @Override
    public ParallelizeLoop create(int loopId) {
        return new ParallelizeLoop(loopId, new RunStateMachineFactoryImpl(), waitNotifyStrategy, new InterleaveLoop(agentLogger));
    }

    public AgentLogger agentLogger() {
        return agentLogger;
    }
}
