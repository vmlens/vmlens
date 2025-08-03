package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;


public class ThreadPoolJoin extends AbstractMethodType {

    public static final AbstractMethodType JOIN_ALL = new ThreadPoolJoin();


    private ThreadPoolJoin() {

    }

    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().addThreadPoolJoin(context.name(),context.desc());
    }
}
