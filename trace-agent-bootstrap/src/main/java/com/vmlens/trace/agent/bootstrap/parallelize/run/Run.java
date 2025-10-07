package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;


public interface Run extends RunForCallback {


    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

    void checkAllThreadsJoined();

    boolean isEnded();

}
