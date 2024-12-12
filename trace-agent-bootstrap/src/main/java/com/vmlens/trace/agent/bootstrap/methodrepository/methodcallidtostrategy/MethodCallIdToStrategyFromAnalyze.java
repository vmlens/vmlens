package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.NormalMethodEnterStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.SynchronizedMethodEnterStrategy;
import gnu.trove.set.hash.THashSet;

public class MethodCallIdToStrategyFromAnalyze implements MethodCallIdToStrategy {

    // Visible for Tests
    public static final NormalMethodEnterStrategy NORMAL_METHOD_ENTER_STRATEGY
            = new NormalMethodEnterStrategy();
    public static final SynchronizedMethodEnterStrategy SYNCHRONIZED_METHOD_ENTER_STRATEGY
            = new SynchronizedMethodEnterStrategy();

    private final THashSet<MethodCallId> synchronizedMethods = new THashSet<>();

    @Override
    public MethodEnterStrategy methodEnterStrategy(MethodCallId methodCallId) {
        if (synchronizedMethods.contains(methodCallId)) {
            return SYNCHRONIZED_METHOD_ENTER_STRATEGY;
        }
        return NORMAL_METHOD_ENTER_STRATEGY;
    }

    public void setMethodIsSynchronized(MethodCallId methodCallId) {
        synchronizedMethods.add(methodCallId);
    }
}
