package com.vmlens.trace.agent.bootstrap.inttest.util;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ParallelizeFacadePassThrough extends ParallelizeFacade {

    private final RunForCallback run;

    public ParallelizeFacadePassThrough(RunForCallback run) {
        super(null);
        this.run = run;
    }


    @Override
    public void newTask(QueueIn queueIn, ThreadLocalForParallelize threadLocalForParallelize,
                        RunnableOrThreadWrapper beganTask) {
         run.newTask(new NewTaskContext(queueIn, beganTask, threadLocalForParallelize));
    }
}
