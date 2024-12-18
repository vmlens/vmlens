package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;

public interface MethodCallIdToStrategy {

    MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId);

}
