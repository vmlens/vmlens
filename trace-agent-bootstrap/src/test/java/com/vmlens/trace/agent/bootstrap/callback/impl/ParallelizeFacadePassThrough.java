package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ParallelizeFacadePassThrough extends ParallelizeFacade {

    private final RunForCallback run;

    public ParallelizeFacadePassThrough(RunForCallback run) {
        super(null);
        this.run = run;
    }


    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(ThreadLocalForParallelize threadLocalWrapperForParallelize,
                                                                    RunnableOrThreadWrapper beganTask) {
        return run.newTask(beganTask, threadLocalWrapperForParallelize);
    }
}
