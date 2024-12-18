package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;

public interface MethodIdToStrategy {

    MethodEnterExitStrategy methodEnterStrategy(int methodId);

    BeforeMethodCallStrategy beforeMethodCallStrategy(int methodId);

}
