package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;

public interface EventContext {

    int methodId();
    Object object();
    InTestActionProcessor inTestActionProcessor();

}
