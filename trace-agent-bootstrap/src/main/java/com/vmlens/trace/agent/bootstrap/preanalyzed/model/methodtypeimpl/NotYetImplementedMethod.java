package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.NotYetImplementedStrategy;

public class NotYetImplementedMethod  extends AbstractMethodType {

    public static final NotYetImplementedMethod SINGLETON = new NotYetImplementedMethod();

    private NotYetImplementedMethod() {
    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addPreAnalyzedMethod(context.name(),context.desc(),
                new NotYetImplementedStrategy(context.className(),context.name()));
    }
}
