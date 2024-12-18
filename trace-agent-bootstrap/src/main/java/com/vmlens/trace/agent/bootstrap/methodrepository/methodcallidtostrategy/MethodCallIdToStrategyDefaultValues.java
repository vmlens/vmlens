package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.ThreadLocalForParallelizeProviderImpl;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl.BeforeNormalMethod;
import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl.BeforeThreadStart;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.RunMethodEnterExitStrategy;

public class MethodCallIdToStrategyDefaultValues implements MethodCallIdToStrategy {

    // Visible for Test
    static final BeforeMethodCallStrategy BEFORE_THREAD_START = new BeforeThreadStart();
    static final BeforeMethodCallStrategy BEFORE_NO_OP = new BeforeNormalMethod();

    private final RunMethodEnterExitStrategy runMethodEnterStrategy;
    private final MethodCallIdToStrategy resourceMap;
    private final MethodCallIdToStrategy analyzeMap;

    public MethodCallIdToStrategyDefaultValues(MethodCallIdToStrategy resourceMap,
                                               MethodCallIdToStrategy analyzeMap) {
        this.resourceMap = resourceMap;
        this.analyzeMap = analyzeMap;
        this.runMethodEnterStrategy = new RunMethodEnterExitStrategy(new CheckIsThreadRun(),
                new ThreadLocalForParallelizeProviderImpl());
    }

    public MethodCallIdToStrategyDefaultValues(MethodCallIdToStrategy resourceMap,
                                               MethodCallIdToStrategy analyzeMap,
                                               CheckIsThreadRun checkIsThreadRun,
                                               ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider) {
        this.resourceMap = resourceMap;
        this.analyzeMap = analyzeMap;
        this.runMethodEnterStrategy = new RunMethodEnterExitStrategy(checkIsThreadRun,
                threadLocalForParallelizeProvider);
    }

    @Override
    public MethodEnterExitStrategy methodEnterStrategy(MethodCallId methodCallId) {
        MethodEnterExitStrategy fromResource = resourceMap.methodEnterStrategy(methodCallId);
        if (fromResource != null) {
            return fromResource;
        }
        if ("run".equals(methodCallId.name()) && "()V".equals(methodCallId.descriptor())) {
            return runMethodEnterStrategy;
        }
        return analyzeMap.methodEnterStrategy(methodCallId);
    }

    public BeforeMethodCallStrategy beforeMethodCallStrategy(MethodCallId methodCallId) {
        if ("start".equals(methodCallId.name()) && "()V".equals(methodCallId.descriptor())) {
            return BEFORE_THREAD_START;
        }
        return BEFORE_NO_OP;
    }

    // visible for test
    public RunMethodEnterExitStrategy runMethodEnterStrategy() {
        return runMethodEnterStrategy;
    }
}
