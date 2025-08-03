package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.MethodTypeContext;

public class ThreadPoolStart extends AbstractMethodType{

    public static final AbstractMethodType SINGLETON = new ThreadPoolStart();

    private ThreadPoolStart() {
    }
    
    @Override
    public void add(MethodTypeContext context) {
        context.methodBuilder().setThreadPoolStart(context.name(),context.desc());
    }
}
