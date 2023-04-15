package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunFactory;

public class RunFactoryImpl implements RunFactory {
    @Override
    public Run create(int maxRunId, CalculatedRun next) {
        return new SynchronizedRunAdapter(new RunStateMachineImpl(next));
    }
}
