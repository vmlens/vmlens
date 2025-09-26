package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public class InTestActionProcessor {

    private final QueueIn eventQueueInternal;
    private final ThreadLocalWhenInTest dataWhenInTest;
    private final StacktraceDepthProvider stacktraceDepthProvider;

    public InTestActionProcessor(QueueIn eventQueueInternal,
                                 ThreadLocalWhenInTest dataWhenInTest,
                                 StacktraceDepthProvider stacktraceDepthProvider) {
        this.eventQueueInternal = eventQueueInternal;
        this.dataWhenInTest = dataWhenInTest;
        this.stacktraceDepthProvider = stacktraceDepthProvider;
    }

    public void process(InTestAction inTestAction) {
        if(inTestAction.checkOrSetDoNotTrace(dataWhenInTest,stacktraceDepthProvider)) {
            if(inTestAction.notInAtomicCallback(dataWhenInTest)) {
                inTestAction.execute(dataWhenInTest,eventQueueInternal);
            }
        }
    }
}
