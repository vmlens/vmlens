package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public interface NewTaskContext extends EventContext {

    ParallelizeFacade parallelizeFacade();

}
