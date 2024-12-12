package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues;
import com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze;
import com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromResource;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;
import gnu.trove.map.hash.THashMap;

public class MethodRepository implements MethodCallIdMap {

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

    public synchronized MethodEnterStrategy methodEnterStrategy(int methodId) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        return methodCallIdToStrategyDefaultValues.methodEnterStrategy(methodCallId);
    }
}
