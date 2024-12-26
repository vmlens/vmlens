package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;


public class InterleaveLoop implements IteratorQueue {
    private final THashSet<AlternatingOrderContainer> allAlternatingOrderContainer =
            new THashSet<>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> stillToBeProcessedAlternatingOrderContainer =
            new TLinkedList<>();
    private final InterleaveLoopIterator iterator;

    public InterleaveLoop() {
        this.iterator = new InterleaveLoopIterator(this);
    }

    // Visible for Test


    public Iterator<CalculatedRun> iterator() {
        return iterator;
    }

    public Iterator<CalculatedRun> poll() {
        if (stillToBeProcessedAlternatingOrderContainer.isEmpty()) {
            return null;
        }
        return stillToBeProcessedAlternatingOrderContainer.removeFirst().element().iterator();
    }

    public void addActualRun(ActualRun actualRun) {
        addActualRun(actualRun.run());
    }

    void addActualRun(TLinkedList<TLinkableWrapper<InterleaveAction>> run) {
        AlternatingOrderContainer container = new AlternatingOrderContainerFactory().create(run);
        if (!allAlternatingOrderContainer.contains(container)) {
            allAlternatingOrderContainer.add(container);
            stillToBeProcessedAlternatingOrderContainer
                    .add(new TLinkableWrapper<>(container));
        }
    }

}
