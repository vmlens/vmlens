package com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.MethodEnterExitStrategy;

public class MethodCallIdToStrategyFromResource implements MethodCallIdToStrategy {

    @Override
    public MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId) {
        return null;
    }

}
