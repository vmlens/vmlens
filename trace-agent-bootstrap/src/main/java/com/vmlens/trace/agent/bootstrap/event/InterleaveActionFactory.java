package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface InterleaveActionFactory {

    InterleaveAction create();

}
