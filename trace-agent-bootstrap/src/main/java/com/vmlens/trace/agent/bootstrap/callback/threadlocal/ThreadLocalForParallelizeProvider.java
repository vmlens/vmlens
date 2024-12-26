package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public interface ThreadLocalForParallelizeProvider {

    ThreadLocalForParallelize threadLocalForParallelize();

}
