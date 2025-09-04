package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.element.LoopRunAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.event.EventTypeThread;

public class ThreadRunElementType implements RunElementType {

    private final EventTypeThread eventTypeThread;
    private final UIThreadOperation threadOperation;
    
    private final LoopRunAndThreadIndex onThreadIndex;

    public ThreadRunElementType(EventTypeThread eventTypeThread, 
                                UIThreadOperation threadOperation, 
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
        return context.threadName(onThreadIndex);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
