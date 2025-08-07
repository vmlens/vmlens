package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadJoinStrategy;

public class ThreadJoin extends AbstractMethodType {

    public static final AbstractMethodType SINGLETON = new ThreadJoin();

    private ThreadJoin() {
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(), context.desc(), ThreadJoinStrategy.SINGLETON);
    }


}
