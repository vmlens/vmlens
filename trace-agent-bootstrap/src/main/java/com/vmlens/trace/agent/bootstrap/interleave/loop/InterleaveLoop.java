package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.AgentLogger;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;



public class InterleaveLoop {

    private final AgentLogger agentLogger;
    private final THashSet<AlternatingOrderContainer> allAlternatingOrderContainer =
            new THashSet<>();

    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> stillToBeProcessedAlternatingOrderContainer =
            new TLinkedList<>();

    private final InterleaveLoopIterator iterator = new InterleaveLoopIterator(this);

    // visible for test
    public static void debug(AgentLogger agentLogger, TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
        for(TLinkableWrapper<InterleaveActionWithPositionFactory> element : actualRun) {
            agentLogger.debug(element.element.toString());
        }
    }

    public InterleaveLoop(AgentLogger agentLogger) {
        this.agentLogger = agentLogger;
    }

    public Iterator<ActualRun> iterator() {
        return iterator;
  }

    AlternatingOrderContainer getAndRemoveAlternatingOrderContainer() {
        return stillToBeProcessedAlternatingOrderContainer.removeFirst().element;
    }
    boolean alternatingOrderContainerAvailable() {
        return ! stillToBeProcessedAlternatingOrderContainer.isEmpty();
    }
   void addActualRun(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
        debug(actualRun);
       AlternatingOrderContainer container = create(actualRun);
       container.debug(agentLogger);
       if(! allAlternatingOrderContainer.contains(container)) {
           allAlternatingOrderContainer.add(container);
           stillToBeProcessedAlternatingOrderContainer
                   .add(new TLinkableWrapper<>(container));
       }
   }

    AgentLogger agentLogger() {
        return agentLogger;
    }

   private AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
       AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();
       BlockBuilderAndCalculatedRunElementContainer container = new BlockBuilderAndCalculatedRunElementContainerFactory().create(actualRun);
       return factory.create(container.runWithPosition,container.run);
   }

    private void debug(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
        if(agentLogger.isDebugEnabled()) {
            debug(agentLogger,actualRun);
        }
    }


}
