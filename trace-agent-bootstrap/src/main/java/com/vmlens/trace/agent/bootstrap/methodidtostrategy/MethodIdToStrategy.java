package com.vmlens.trace.agent.bootstrap.methodidtostrategy;

import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.MethodEnterExitStrategy;

public interface MethodIdToStrategy {

    MethodEnterExitStrategy methodEnterStrategy(int methodId);

    BeforeMethodCallStrategy beforeMethodCallStrategy(int methodId);

}
