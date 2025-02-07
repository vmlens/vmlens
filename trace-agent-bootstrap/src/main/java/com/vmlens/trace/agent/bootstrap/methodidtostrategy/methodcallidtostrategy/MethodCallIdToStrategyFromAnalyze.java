package com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodidtostrategy.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.MethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.NormalMethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.SynchronizedMethodEnterExitStrategy;
import gnu.trove.set.hash.THashSet;

public class MethodCallIdToStrategyFromAnalyze implements MethodCallIdToStrategy {

    // Visible for Tests
    public static final NormalMethodEnterExitStrategy NORMAL_METHOD_ENTER_STRATEGY
            = new NormalMethodEnterExitStrategy();
    public static final SynchronizedMethodEnterExitStrategy SYNCHRONIZED_METHOD_ENTER_STRATEGY
            = new SynchronizedMethodEnterExitStrategy();

    private final THashSet<MethodCallId> synchronizedMethods = new THashSet<>();

    @Override
    public MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId) {
        if (synchronizedMethods.contains(methodCallId)) {
            return SYNCHRONIZED_METHOD_ENTER_STRATEGY;
        }
        return NORMAL_METHOD_ENTER_STRATEGY;
    }

    public void setMethodIsSynchronized(MethodCallId methodCallId) {
        synchronizedMethods.add(methodCallId);
    }
}
