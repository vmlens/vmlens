package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;

public class MethodCallIdToStrategyFromResource implements MethodCallIdToStrategy {

    @Override
    public MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId) {
        return null;
    }

}
