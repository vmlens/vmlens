package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import gnu.trove.map.hash.THashMap;

public class MethodRepositoryImpl implements MethodRepositoryForTransform, MethodRepositoryForCallback {

    private final THashMap<Integer, EitherAllOrPreAnalyzed> idToEither = new THashMap<>();
    private final THashMap<MethodCallId, Integer> methodCallIdToInteger = new THashMap<>();
    private int maxIndex = 0;

    public synchronized int asInt(MethodCallId methodCallId) {
        if (methodCallIdToInteger.contains(methodCallId)) {
            return methodCallIdToInteger.get(methodCallId);
        }
        int temp = maxIndex;
        maxIndex++;
        methodCallIdToInteger.put(methodCallId, temp);
        return temp;
    }

    public synchronized StrategyAll strategyAll(int methodId) {
        return idToEither.get(methodId).strategyAll();
    }

    @Override
    public synchronized StrategyPreAnalyzed strategyPreAnalyzed(int methodId) {
        return idToEither.get(methodId).strategyPreAnalyzed();
    }

    @Override
    public synchronized void setStrategyAll(int methodId, StrategyAll strategyAll) {
        idToEither.put(methodId, new EitherAll(strategyAll));
    }

    @Override
    public synchronized void setStrategyPreAnalyzed(int methodId, StrategyPreAnalyzed strategyPreAnalyzed) {
        idToEither.put(methodId, new EitherPreAnalyzed(strategyPreAnalyzed));
    }



}
