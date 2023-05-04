package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ActualRun {
    private final CalculatedRun calculatedRun;
    public ActualRun(CalculatedRun calculatedRun) {
        this.calculatedRun = calculatedRun;
    }
    public boolean isActive(int threadId) {
        return calculatedRun.isActive(threadId);
    }
    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun =
            new TLinkedList<>();
    public void after(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory) {
        actualRun.add(new TLinkableWrapper(interleaveActionWithPositionFactory));
        calculatedRun.incrementPositionInThread();
    }
    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun() {
        return actualRun;
    }

    public void debug(AgentLogger agentLogger) {
        calculatedRun.debug(agentLogger);
    }
}
