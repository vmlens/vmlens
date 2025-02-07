package com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.MethodEnterExitStrategy;

public interface MethodCallIdToStrategy {

    MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId);

}
