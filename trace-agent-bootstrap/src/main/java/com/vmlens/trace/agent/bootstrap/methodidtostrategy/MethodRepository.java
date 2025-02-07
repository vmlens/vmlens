package com.vmlens.trace.agent.bootstrap.methodidtostrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze;
import com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy.MethodCallIdToStrategyFromResource;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategy.MethodEnterExitStrategy;
import gnu.trove.map.hash.THashMap;

public class MethodRepository implements MethodRepositoryForAnalyze, MethodIdToStrategy {

    private final MethodCallIdToStrategyDefaultValues methodCallIdToStrategyDefaultValues;
    private final MethodCallIdToStrategyFromAnalyze methodCallIdToStrategyFromAnalyze;

    private final THashMap<Integer, MethodCallId> integerToMethodCallId = new THashMap<>();
    private final THashMap<MethodCallId, Integer> methodCallIdToInt = new THashMap<>();
    private int maxIndex = 0;

    public MethodRepository() {
        methodCallIdToStrategyFromAnalyze = new MethodCallIdToStrategyFromAnalyze();
        methodCallIdToStrategyDefaultValues = new MethodCallIdToStrategyDefaultValues(
                new MethodCallIdToStrategyFromResource(), methodCallIdToStrategyFromAnalyze);
    }

    public MethodRepository(CheckIsThreadRun checkIsThreadRun,
                            ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
                            ParallelizeFacade parallelizeFacade) {
        methodCallIdToStrategyFromAnalyze = new MethodCallIdToStrategyFromAnalyze();
        methodCallIdToStrategyDefaultValues = new MethodCallIdToStrategyDefaultValues(
                new MethodCallIdToStrategyFromResource(), methodCallIdToStrategyFromAnalyze,
                checkIsThreadRun,
                threadLocalForParallelizeProvider, parallelizeFacade);
    }

    public synchronized int asInt(MethodCallId methodCallId) {
        if (methodCallIdToInt.contains(methodCallId)) {
            return methodCallIdToInt.get(methodCallId);
        }
        int temp = maxIndex;
        maxIndex++;
        methodCallIdToInt.put(methodCallId, temp);
        integerToMethodCallId.put(temp, methodCallId);
        return temp;
    }

    public synchronized void setMethodIsSynchronized(MethodCallId methodCallId) {
        methodCallIdToStrategyFromAnalyze.setMethodIsSynchronized(methodCallId);
    }

    public synchronized BeforeMethodCallStrategy beforeMethodCallStrategy(int methodId) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        return methodCallIdToStrategyDefaultValues.beforeMethodCallStrategy(methodCallId);
    }

    public synchronized MethodEnterExitStrategy methodEnterStrategy(int methodId) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        return methodCallIdToStrategyDefaultValues.methodEnterStrategy(methodCallId);
    }

    @Override
    public synchronized int getIdAndSetMethodIsSynchronized(MethodCallId methodCallId) {
        methodCallIdToStrategyFromAnalyze.setMethodIsSynchronized(methodCallId);
        return asInt(methodCallId);
    }
}
