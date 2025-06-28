package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadJoinStrategy;

public class ThreadJoin extends AbstractMethodType {

    public static final AbstractMethodType SINGLETON = new ThreadJoin();

    private ThreadJoin() {
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addPreAnalyzedMethod(name, desc, ThreadJoinStrategy.SINGLETON);
    }


}
