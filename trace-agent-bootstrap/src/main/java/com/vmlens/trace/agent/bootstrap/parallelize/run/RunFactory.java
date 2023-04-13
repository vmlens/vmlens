package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

public interface RunFactory {
    Run create(int maxRunId, CalculatedRun next);
}
