package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.FieldAccessEvent;
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
            if(inTestAction.takeAction(dataWhenInTest)) {
                if(inTestAction instanceof RunAfter) {
                    RunAfter runAfter = (RunAfter) inTestAction;
                    if(runAfter.runtimeEvent() instanceof FieldAccessEvent) {
                        FieldAccessEvent event =  (FieldAccessEvent) runAfter.runtimeEvent();
                        if(event.threadIndex() != 0) {
                            System.out.println(runAfter.runtimeEvent());
                        }
                    }
                }
                inTestAction.execute(dataWhenInTest,eventQueueInternal);
            }
        }
    }
}
