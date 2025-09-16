package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class InTestActionProcessor {

    private final QueueIn eventQueueInternal;
    private final ThreadLocalWhenInTest dataWhenInTest;

    public InTestActionProcessor(QueueIn eventQueueInternal,
                                 ThreadLocalWhenInTest dataWhenInTest) {
        this.eventQueueInternal = eventQueueInternal;
        this.dataWhenInTest = dataWhenInTest;
    }

    public void process(InTestAction inTestAction) {
        if(inTestAction.notInAtomicCallback(dataWhenInTest)) {
            inTestAction.execute(dataWhenInTest,eventQueueInternal);
        }
    }
}
