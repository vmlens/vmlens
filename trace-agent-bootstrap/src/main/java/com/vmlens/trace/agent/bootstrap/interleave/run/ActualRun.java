package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {
    private final ActualRunObserver actualRunObserver;

    public ActualRun(ActualRunObserver actualRunObserver) {
        this.actualRunObserver = actualRunObserver;
    }

    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> run =
            new TLinkedList<>();


    public void after(RuntimeEvent runtimeEvent,
                      ActualRunContext actualRunContext) {
        after(runtimeEvent);
        onEvent(runtimeEvent, actualRunContext.sendEventContext());

    }


    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> run() {
        return run;
    }

    // Visible for Test
    public void debug(AgentLogger agentLogger) {
        for (TLinkableWrapper<InterleaveActionWithPositionFactory> element : run) {
            agentLogger.debug(this.getClass(), element.element.toString());
        }
    }

    void after(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory) {
        run.add(new TLinkableWrapper(interleaveActionWithPositionFactory));
        actualRunObserver.after(interleaveActionWithPositionFactory);
    }

    void onEvent(RuntimeEvent event, SendEventContext sendEventContext) {
        event.send(sendEventContext);
    }

}
