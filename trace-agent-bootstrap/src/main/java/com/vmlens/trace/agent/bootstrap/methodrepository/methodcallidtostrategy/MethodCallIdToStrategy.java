package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;

public interface MethodCallIdToStrategy {

    MethodEnterStrategy methodEnterStrategy(MethodCallId methodCallId);

}
