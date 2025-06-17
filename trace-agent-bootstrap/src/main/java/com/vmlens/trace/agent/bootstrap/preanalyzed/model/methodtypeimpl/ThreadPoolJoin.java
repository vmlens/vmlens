package com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.JoinAll;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.JoinTask;
import com.vmlens.trace.agent.bootstrap.strategy.threadpool.StrategyThreadPool;

public class ThreadPoolJoin extends AbstractMethodType {

    public static final AbstractMethodType JOIN_ALL = new ThreadPoolJoin(new JoinAll());
    public static final AbstractMethodType JOIN_TASK = new ThreadPoolJoin(new JoinTask());

    private final StrategyThreadPool strategyThreadPool;

    private ThreadPoolJoin(StrategyThreadPool strategyThreadPool) {
        this.strategyThreadPool = strategyThreadPool;
    }

    @Override
    public void add(String name, String desc, FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder) {
        methodBuilder.addThreadPoolJoin(name,desc,strategyThreadPool);
    }
}
