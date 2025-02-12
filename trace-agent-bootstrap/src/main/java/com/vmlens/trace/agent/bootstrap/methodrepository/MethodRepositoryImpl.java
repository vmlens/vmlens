package com.vmlens.trace.agent.bootstrap.methodrepository;

import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.StrategyAll;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import gnu.trove.map.hash.THashMap;

public class MethodRepositoryImpl implements MethodRepositoryForTransform, MethodRepositoryForCallback {

    private final THashMap<Integer, MethodCallId> integerToMethodCallId = new THashMap<>();
    private final THashMap<MethodCallId, EitherEmptyAllOrPreAnalyzed> methodCallIdToEither = new THashMap<>();

    private final CheckIsThreadRun checkIsThreadRun;
    private int maxIndex = 0;

    public MethodRepositoryImpl(CheckIsThreadRun checkIsThreadRun) {
        this.checkIsThreadRun = checkIsThreadRun;
    }

    public static MethodRepositoryImpl create() {
        return new MethodRepositoryImpl(new CheckIsThreadRun());
    }


    public synchronized int asInt(MethodCallId methodCallId) {
        return getOrCreateEither(methodCallId).methodId();
    }

    public synchronized StrategyAll strategyAll(int methodId) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        return methodCallIdToEither.get(methodCallId).strategyAll();
    }

    @Override
    public StrategyPreAnalyzed strategyPreAnalyzed(int methodId) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        return methodCallIdToEither.get(methodCallId).strategyPreAnalyzed();
    }

    @Override
    public void setStrategyAll(int methodId, StrategyAll strategyAll) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        EitherEmptyAllOrPreAnalyzed either = getOrCreateEither(methodCallId);
        either.setStrategyAll(strategyAll);
    }

    @Override
    public void setStrategyPreAnalyzed(int methodId, StrategyPreAnalyzed strategyPreAnalyzed) {
        MethodCallId methodCallId = integerToMethodCallId.get(methodId);
        EitherEmptyAllOrPreAnalyzed either = getOrCreateEither(methodCallId);
        either.setStrategyPreAnalyzed(strategyPreAnalyzed);
    }

    private EitherEmptyAllOrPreAnalyzed getOrCreateEither(MethodCallId methodCallId) {
        if (methodCallIdToEither.contains(methodCallId)) {
            return methodCallIdToEither.get(methodCallId);
        }
        EitherEmptyAllOrPreAnalyzed temp = new EitherEmptyAllOrPreAnalyzed(maxIndex);
        maxIndex++;
        methodCallIdToEither.put(methodCallId, temp);
        integerToMethodCallId.put(temp.methodId(), methodCallId);
        return temp;
    }
}
