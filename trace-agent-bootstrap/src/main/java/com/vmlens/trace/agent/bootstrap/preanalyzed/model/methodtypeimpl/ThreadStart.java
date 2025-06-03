package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodType;

public class ThreadStart extends AbstractMethodType {

    public static final AbstractMethodType SINGLETON = new ThreadStart();

    private ThreadStart() {
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addThreadStart(name, desc);
    }
}
