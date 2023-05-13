package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {
    private final ActualRunObserver actualRunObserver;

    public ActualRun(ActualRunObserver actualRunObserver) {
        this.actualRunObserver = actualRunObserver;
    }

    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun =
            new TLinkedList<>();

    public void after(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory) {
        actualRun.add(new TLinkableWrapper(interleaveActionWithPositionFactory));
        actualRunObserver.after(interleaveActionWithPositionFactory);
    }

    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun() {
        return actualRun;
    }

    // Visible for Test
    public void debug(AgentLogger agentLogger) {
        for (TLinkableWrapper<InterleaveActionWithPositionFactory> element : actualRun) {
            agentLogger.debug(element.element.toString());
        }
    }

}
