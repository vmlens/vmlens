package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapperForParallelize;

import java.util.LinkedList;
import java.util.List;

public class CallbackStatePerThreadForParallelizeMock extends PerThreadCounter implements ThreadLocalWrapperForParallelize {

    private final List<StaticEvent> actualEvents = new LinkedList<StaticEvent>();

    @Override
    public long threadId() {
        return 0;
    }

    @Override
    public void offer(StaticEvent element) {
        actualEvents.add(element);
    }

    @Override
    public ParallelizedThreadLocal getParallelizedThreadLocal() {
        return null;
    }

    @Override
    public void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal) {

    }

    public List<StaticEvent> actualEvents() {
        return actualEvents;
    }
}
