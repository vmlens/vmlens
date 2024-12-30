package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;


public interface Run extends RunForCallback {

    int runId();

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);


}
