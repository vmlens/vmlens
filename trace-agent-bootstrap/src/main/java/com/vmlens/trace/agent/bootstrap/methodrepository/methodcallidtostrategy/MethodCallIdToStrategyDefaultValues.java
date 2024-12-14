package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl.BeforeNormalMethod;
import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl.BeforeThreadStart;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl.RunMethodEnterStrategy;

public class MethodCallIdToStrategyDefaultValues implements MethodCallIdToStrategy {

    // Visible for Test
    static final RunMethodEnterStrategy RUN_METHOD_ENTER_STRATEGY = new RunMethodEnterStrategy();
    static final BeforeMethodCallStrategy BEFORE_THREAD_START = new BeforeThreadStart();
    static final BeforeMethodCallStrategy BEFORE_NO_OP = new BeforeNormalMethod();

    private final MethodCallIdToStrategy resourceMap;
    private final MethodCallIdToStrategy analyzeMap;

    public MethodCallIdToStrategyDefaultValues(MethodCallIdToStrategy resourceMap,
                                               MethodCallIdToStrategy analyzeMap) {
        this.resourceMap = resourceMap;
        this.analyzeMap = analyzeMap;
    }

    @Override
    public MethodEnterStrategy methodEnterStrategy(MethodCallId methodCallId) {
        MethodEnterStrategy fromResource = resourceMap.methodEnterStrategy(methodCallId);
        if (fromResource != null) {
            return fromResource;
        }
        if ("run".equals(methodCallId.name()) && "()V".equals(methodCallId.descriptor())) {
            return RUN_METHOD_ENTER_STRATEGY;
        }
        return analyzeMap.methodEnterStrategy(methodCallId);
    }

    public BeforeMethodCallStrategy beforeMethodCallStrategy(MethodCallId methodCallId) {
        if ("start".equals(methodCallId.name()) && "()V".equals(methodCallId.descriptor())) {
            return BEFORE_THREAD_START;
        }
        return BEFORE_NO_OP;
    }
}
