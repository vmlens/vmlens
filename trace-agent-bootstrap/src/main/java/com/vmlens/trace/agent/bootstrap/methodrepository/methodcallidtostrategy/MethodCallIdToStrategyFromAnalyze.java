package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.NormalMethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.SynchronizedMethodEnterExitStrategy;
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
