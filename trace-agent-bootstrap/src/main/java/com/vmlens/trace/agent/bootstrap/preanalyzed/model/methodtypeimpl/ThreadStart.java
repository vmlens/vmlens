package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;

public class ThreadStart extends AbstractMethodType {

    public static final AbstractMethodType SINGLETON = new ThreadStart();

    private ThreadStart() {
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(), context.desc()
                , ThreadStartStrategy.SINGLETON);
    }
}
