package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;

public class SingleThreadFilter {

    private boolean isSingleThreaded = true;

    /*
     * checking for thread stops is not easy since even if
     * a test is terminated we need to wait for the jpin
     * since the main thread might execute statements which lead to
     * a data race
     */

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
