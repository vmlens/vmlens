package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class InterleaveLoop implements IteratorQueue {


    private final TLinkedList<TLinkableWrapper<ThreadIndexToElementList<InterleaveAction>>> alreadyProcessed =
         new TLinkedList<>();

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
        ThreadIndexToElementList<InterleaveAction> newRun = create(run);
        if(alreadyProcessed(newRun))  {
            return;
        }
        alreadyProcessed.add(wrap(newRun));
        AlternatingOrderContainer container = new AlternatingOrderContainerFactory().create(run);
        stillToBeProcessedAlternatingOrderContainer
                    .add(new TLinkableWrapper<>(container));
    }

    private  ThreadIndexToElementList<InterleaveAction> create(TLinkedList<TLinkableWrapper<InterleaveAction>> run) {
        ThreadIndexToElementList<InterleaveAction> result = new ThreadIndexToElementList<>();
        for (TLinkableWrapper<InterleaveAction> syncAction : run) {
            result.add(syncAction.element());
        }
        return result;
    }

    private boolean alreadyProcessed(ThreadIndexToElementList<InterleaveAction> newRun) {
        for(TLinkableWrapper<ThreadIndexToElementList<InterleaveAction>> element : alreadyProcessed) {
          if(isSame(element.element(),newRun)) {
              return true;
          }
        }
        return false;
    }

    private boolean isSame(ThreadIndexToElementList<InterleaveAction> existing, ThreadIndexToElementList<InterleaveAction> newRun) {
        if(existing.elementCount() != newRun.elementCount()) {
            return false;
        }
        NormalizeContext normalizeContext = new NormalizeContext();
        Iterator<InterleaveAction> newIter = newRun.elementIterator();
        Iterator<InterleaveAction> existingIter = existing.elementIterator();
        while(newIter.hasNext()) {
            // we need to call existingIter.hasNext() since in hasNext is the logi
            // to move the elements forward
            existingIter.hasNext();
            InterleaveAction existingAction  = existingIter.next();
            InterleaveAction newAction = newIter.next();
            if(! existingAction.equalsNormalized(normalizeContext,newAction)) {
                return false;
            }
        }
        return true;
    }

}
