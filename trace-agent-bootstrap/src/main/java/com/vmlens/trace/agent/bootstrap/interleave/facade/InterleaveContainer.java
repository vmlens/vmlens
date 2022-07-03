package com.vmlens.trace.agent.bootstrap.interleave.facade;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.AlternatingOrderFactory;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * Responsible for storing all AlternativeOrderFactory and the current AlternativeOrderContainer
 * current AlternativeOrderContainer
 * queue for AlternativeOrderFactory
 * List all AlternativeOrderFactory
 * <p>
 * Testbarkeit
 * <p>
 * addActualRun
 * next CalculatedRun
 * hasNext
 * <p>
 */
public class InterleaveContainer {

    private final Logger logger;
    private final TLinkedList<TLinkableWrapper<Iterable<CalculatedRun>>> alternativeOrderFactoryQueue =
            new TLinkedList<TLinkableWrapper<Iterable<CalculatedRun>>>();
    private final TLinkedList<TLinkableWrapper<Iterable<CalculatedRun>>> allAlternativeOrderFactoryList =
            new TLinkedList<TLinkableWrapper<Iterable<CalculatedRun>>>();
    private Iterator<CalculatedRun> currentAlternatingOrder;

    public InterleaveContainer(Logger logger) {
        this.logger = logger;
    }

    public boolean addActualRun(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> run) {
        Iterable<CalculatedRun> factory = create(run);
        boolean found = false;
        for (TLinkableWrapper<Iterable<CalculatedRun>> elem : allAlternativeOrderFactoryList) {
            if (elem.element.equals(factory)) {
                found = true;
                break;
            }
        }
        // ToDo hier info logggen
        if (!found) {
            logger.interleaveInfo(InterleaveContainer.class, "added " + factory.toString());
            allAlternativeOrderFactoryList.add(new TLinkableWrapper<Iterable<CalculatedRun>>(factory));
            alternativeOrderFactoryQueue.addFirst(new TLinkableWrapper<Iterable<CalculatedRun>>(factory));
            return true;
        }
        return false;
    }

    public boolean hasNext() {
        while (true) {
            if (currentAlternatingOrder != null && currentAlternatingOrder.hasNext()) {
                return true;
            }
            if (alternativeOrderFactoryQueue.isEmpty()) {
                return false;
            }
            currentAlternatingOrder = alternativeOrderFactoryQueue.removeLast().element.iterator();
        }
    }

    public CalculatedRun next() {
        return currentAlternatingOrder.next();
    }

    // To make mocking of Iterable<CalculatedRun> easier
    protected Iterable<CalculatedRun> create(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> run) {
        return new AlternatingOrderFactory(logger).create(run);
    }

}
