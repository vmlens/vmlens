package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;


public class ThreadPoolJoin extends AbstractMethodType {

    public static final AbstractMethodType JOIN_ALL = new ThreadPoolJoin();


    private ThreadPoolJoin() {

    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addThreadPoolJoin(name,desc);
    }
}
