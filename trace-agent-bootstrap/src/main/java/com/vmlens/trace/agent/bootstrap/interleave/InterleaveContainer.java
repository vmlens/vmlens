package com.vmlens.trace.agent.bootstrap.interleave;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainerIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.AlternatingOrderFactory;
import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

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
 * Fixme test über mocks
 */
public class InterleaveContainer {

    private final Logger logger;
    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> alternativeOrderFactoryQueue =
            new TLinkedList<TLinkableWrapper<AlternatingOrderContainer>>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> allAlternativeOrderFactoryList =
            new TLinkedList<TLinkableWrapper<AlternatingOrderContainer>>();
    private AlternatingOrderContainerIterator currentAlternatingOrder;

    public InterleaveContainer(Logger logger) {
        this.logger = logger;
    }

    public boolean addActualRun(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> run) {
        AlternatingOrderContainer factory = new AlternatingOrderFactory(logger).create(run);
        boolean found = false;
        for (TLinkableWrapper<AlternatingOrderContainer> elem : allAlternativeOrderFactoryList) {
            if (elem.element.equals(factory)) {
                found = true;
                break;
            }
        }
        if (!found) {
            allAlternativeOrderFactoryList.add(new TLinkableWrapper<AlternatingOrderContainer>(factory));
            alternativeOrderFactoryQueue.addFirst(new TLinkableWrapper<AlternatingOrderContainer>(factory));
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

}
