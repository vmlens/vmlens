package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;

public interface ParallelizeLoopFactory {

    ParallelizeLoop create(int loopId, InterleaveLoopContext interleaveLoopContext);

}
