package com.vmlens.report.input.run;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.input.LoopRunAndThreadIndex;
import com.vmlens.report.input.run.objecthashcodemap.ObjectHashCodeMap;
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread;

public class ThreadRunElementType implements RunElementType {

    private final EventTypeThread eventTypeThread;
    private final ReportThreadOperation threadOperation;
    
    private final LoopRunAndThreadIndex onThreadIndex;

    public ThreadRunElementType(EventTypeThread eventTypeThread, 
                                ReportThreadOperation threadOperation,
                                LoopRunAndThreadIndex onThreadIndex) {
        this.eventTypeThread = eventTypeThread;
        this.threadOperation = threadOperation;
        this.onThreadIndex = onThreadIndex;
    }

    @Override
    public String operation() {
        return eventTypeThread.text() + " " + threadOperation.text();
    }
    

    @Override
    public String element(DescriptionContext context) {
        return "";
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }

    @Override
    public void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex) {

    }

    @Override
    public String object(DescriptionContext context) {
        return context.threadName(onThreadIndex);
    }
}
