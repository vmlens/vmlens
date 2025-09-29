package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;

public class SingleThreadFilter {

    public boolean take(EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent,
                        ThreadIndexAndThreadStateMap context) {
        int activeThreads = context.getNotTerminatedThreadCount();
        if(activeThreads > 1) {
            return true;
        }
        InterleaveActionFactory factory = runtimeEvent.asInterleaveActionFactory();
        if(factory == null) {
            return false;
        }
        return factory.startOrStopsThread();
    }
}
