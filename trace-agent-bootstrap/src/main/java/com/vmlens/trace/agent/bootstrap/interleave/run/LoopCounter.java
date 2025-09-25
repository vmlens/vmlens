package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;

public interface LoopCounter {

    InterleaveRunState onPluginEvent(int threadIndex, ThreadIndexAndThreadStateMap context, AfterCallback afterCallback, InterleaveRunState previous);
    InterleaveRunState onInterleaveActionFactory(int threadIndex, ThreadIndexAndThreadStateMap context, AfterCallback afterCallback, InterleaveRunState previous);

}
