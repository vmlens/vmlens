package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRunOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionOld.SyncActionAndPosition;
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
    private final TLinkedList<TLinkableWrapper<Iterable<CalculatedRunOld>>> alternativeOrderFactoryQueue =
            new TLinkedList<TLinkableWrapper<Iterable<CalculatedRunOld>>>();
    private final TLinkedList<TLinkableWrapper<Iterable<CalculatedRunOld>>> allAlternativeOrderFactoryList =
            new TLinkedList<TLinkableWrapper<Iterable<CalculatedRunOld>>>();
    private Iterator<CalculatedRunOld> currentAlternatingOrder;

    public InterleaveContainer(Logger logger) {
        this.logger = logger;
    }

    public boolean addActualRun(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> run) {
        Iterable<CalculatedRunOld> factory = create(run);
        boolean found = false;
        for (TLinkableWrapper<Iterable<CalculatedRunOld>> elem : allAlternativeOrderFactoryList) {
            if (elem.element.equals(factory)) {
                found = true;
                break;
            }
        }
        // ToDo hier info logggen
        if (!found) {
            logger.interleaveInfo(InterleaveContainer.class, "added " + factory.toString());
            allAlternativeOrderFactoryList.add(new TLinkableWrapper<Iterable<CalculatedRunOld>>(factory));
            alternativeOrderFactoryQueue.addFirst(new TLinkableWrapper<Iterable<CalculatedRunOld>>(factory));
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

    public CalculatedRunOld next() {
        return currentAlternatingOrder.next();
    }

    // To make mocking of Iterable<CalculatedRun> easier
    protected Iterable<CalculatedRunOld> create(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> run) {
        return new CreateAlternatingOrderFromSyncAction(logger).create(run);
    }

}
