package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;

public class ThreadPoolStart extends AbstractMethodType{

    public static final AbstractMethodType SINGLETON = new ThreadPoolStart();

    private ThreadPoolStart() {
    }
    
    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.setThreadPoolStart(name,desc);
    }
}
