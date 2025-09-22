package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;

public class SingleThreadFilter {

    private boolean isSingleThreaded = true;

    public boolean take(EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent) {
        if(! isSingleThreaded) {
            return true;
        }
        InterleaveActionFactory factory = runtimeEvent.asInterleaveActionFactory();
        if(factory == null) {
            return false;
        }
        if(factory.startsNewThread()) {
            isSingleThreaded = false;
            return true;
        }
        return false;
    }
}
