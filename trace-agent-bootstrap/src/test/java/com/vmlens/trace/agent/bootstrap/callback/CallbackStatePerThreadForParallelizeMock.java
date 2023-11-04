package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapperForParallelize;
import com.vmlens.trace.agent.bootstrap.testFixture.StaticEventAndId;

import java.util.LinkedList;
import java.util.List;

public class CallbackStatePerThreadForParallelizeMock extends PerThreadCounter implements ThreadLocalWrapperForParallelize {

    private final List<StaticEventAndId> actualEvents = new LinkedList<StaticEventAndId>();

    @Override
    public long threadId() {
        return 0;
    }

    @Override
    public void put(int index, StaticEvent element, int slidingWindowId) {
        actualEvents.add(new StaticEventAndId(element, index));
    }

    @Override
    public ParallelizedThreadLocal getParallelizedThreadLocal() {
        return null;
    }

    @Override
    public void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal) {

    }

    public List<StaticEventAndId> actualEvents() {
        return actualEvents;
    }
}
