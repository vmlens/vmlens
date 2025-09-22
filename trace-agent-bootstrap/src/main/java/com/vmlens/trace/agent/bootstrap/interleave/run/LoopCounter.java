package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;

public interface LoopCounter {

    InterleaveRunState onPluginEvent(int threadIndex, ThreadIndexAndThreadStateMap context, SendEvent sendEvent, InterleaveRunState previous);
    InterleaveRunState onInterleaveActionFactory(int threadIndex, ThreadIndexAndThreadStateMap context, SendEvent sendEvent, InterleaveRunState previous);

}
