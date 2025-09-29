package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface EitherPluginEventOnlyOrInterleaveActionFactory {

    /**
     * returns null if it is not an InterleaveActionFactory
     * @return
     */
    InterleaveActionFactory asInterleaveActionFactory();

    /**
     * returns null if it is not an PluginEventOnly
     * @return
     */
    PluginEventOnly asPluginEventOnly();

}
