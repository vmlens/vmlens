package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;



public class InterleaveLoop {

    private final THashSet<AlternatingOrderContainer> allAlternatingOrderContainer =
            new THashSet<>();

    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> stillToBeProcessedAlternatingOrderContainer =
            new TLinkedList<>();

    private final InterleaveLoopIterator iterator = new InterleaveLoopIterator(this);

    public Iterator<CalculatedRun> iterator() {
        return iterator;
  }

    AlternatingOrderContainer getAndRemoveAlternatingOrderContainer() {
        return stillToBeProcessedAlternatingOrderContainer.removeFirst().element;
    }
    boolean alternatingOrderContainerAvailable() {
        return ! stillToBeProcessedAlternatingOrderContainer.isEmpty();
    }
   void addActualRun(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
       AlternatingOrderContainer container = create(actualRun);
       if(! allAlternatingOrderContainer.contains(container)) {
           allAlternatingOrderContainer.add(container);
           stillToBeProcessedAlternatingOrderContainer
                   .add(new TLinkableWrapper<>(container));
       }
   }

   private AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
       AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();
       return factory.create(actualRun);
   }



}
