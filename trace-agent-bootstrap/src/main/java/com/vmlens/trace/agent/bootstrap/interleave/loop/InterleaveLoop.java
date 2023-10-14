package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;


public class InterleaveLoop implements IteratorQueue {
    private final AgentLogger agentLogger;
    private final THashSet<AlternatingOrderContainer> allAlternatingOrderContainer =
            new THashSet<>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> stillToBeProcessedAlternatingOrderContainer =
            new TLinkedList<>();
    private final InterleaveLoopIterator iterator;

    public InterleaveLoop(AgentLogger agentLogger) {
        this.agentLogger = agentLogger;
        this.iterator = new InterleaveLoopIterator(this, agentLogger);
    }

    // Visible for Test


    public Iterator<CalculatedRun> iterator() {
        return iterator;
    }

    public Iterator<CalculatedRun> poll() {
        if (stillToBeProcessedAlternatingOrderContainer.isEmpty()) {
            return null;
        }
        return stillToBeProcessedAlternatingOrderContainer.removeFirst().element.iterator();
    }

    public void addActualRun(ActualRun actualRun) {
        actualRun.debug(agentLogger);
        addActualRun(actualRun.run());
    }

    // Visible for Test
    void addActualRun(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> run) {
        AlternatingOrderContainer container = new AlternatingOrderContainerFactory().create(run);
        container.debug(agentLogger);
        if (!allAlternatingOrderContainer.contains(container)) {
            allAlternatingOrderContainer.add(container);
            stillToBeProcessedAlternatingOrderContainer
                    .add(new TLinkableWrapper<>(container));
        }
    }

}
