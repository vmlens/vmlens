package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;

public class MethodCallIdToStrategyFromResource implements MethodCallIdToStrategy {

    @Override
    public MethodEnterStrategy methodEnterStrategy(MethodCallId methodCallId) {
        return null;
    }

}
