package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;

public interface MethodIdToStrategy {

    MethodEnterStrategy methodEnterStrategy(int methodId);

    BeforeMethodCallStrategy beforeMethodCallStrategy(int methodId);

}
