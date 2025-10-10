package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop.InterleaveActionLoopFactory;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.patterndetection.DetectAndReplacePattern;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class InterleaveLoop implements IteratorQueue {

    private static final boolean TRACE_INTERLEAVE_ACTIONS = true;

    private final InterleaveLoopContext interleaveLoopContext;
    private final TLinkedList<TLinkableWrapper<ThreadIndexToElementList<InterleaveAction>>> alreadyProcessed =
         new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderContainer>> stillToBeProcessedAlternatingOrderContainer =
            new TLinkedList<>();
    private final InterleaveLoopIterator iterator;
    private boolean containsLoop;

    public InterleaveLoop(InterleaveLoopContext interleaveLoopContext) {
        this.interleaveLoopContext = interleaveLoopContext;
        this.iterator = new InterleaveLoopIterator(interleaveLoopContext,this);
    }

    public static AlternatingOrderContainer createAlternatingOrderContainer(ThreadIndexToElementList<InterleaveAction> orig,
                                                                            InterleaveLoopContext interleaveLoopContext) {
        TLinkedList<TLinkableWrapper<InterleaveAction>> replaced = new DetectAndReplacePattern().replace(orig);
         return new AlternatingOrderContainerFactory().create(replaced, interleaveLoopContext);
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

    public void addActualRun(ActualRun actualRun,
                             QueueIn queueIn) {
        containsLoop = actualRun.containsLoop() | containsLoop;
        TLinkedList<TLinkableWrapper<InterleaveAction>> withLoops = actualRun.run();

        if(containsLoop)  {
            withLoops = new InterleaveActionLoopFactory().create(actualRun.run());
        }

        if(TRACE_INTERLEAVE_ACTIONS) {
            String[] array = new String[withLoops.size() + 1];
            array[0] = "New Run";
            int index = 1;
            for(TLinkableWrapper<InterleaveAction> elem : withLoops) {
                array[index] = elem.element().toString();
                index++;
            }
            queueIn.offer(new InfoMessageEvent(array));
        }

        addActualRunWithLoops(withLoops);
    }

    public InterleaveLoopContext interleaveLoopContext() {
        return interleaveLoopContext;
    }

    // Visible for test
    static boolean isSame(ThreadIndexToElementList<InterleaveAction> existing,
                           ThreadIndexToElementList<InterleaveAction> newRun) {
        if(existing.elementCount() != newRun.elementCount()) {
            return false;
        }
        Iterator<InterleaveAction> newIter = newRun.elementIterator();
        Iterator<InterleaveAction> existingIter = existing.elementIterator();
        while(newIter.hasNext()) {
            // we need to call existingIter.hasNext() since in hasNext is the logic
            // to move the elements forward
            existingIter.hasNext();
            InterleaveAction existingAction  = existingIter.next();
            InterleaveAction newAction = newIter.next();
            if(! existingAction.equalsNormalized(newAction)) {
                return false;
            }
        }
        return true;
    }

    // Visible for test
    public static ThreadIndexToElementList<InterleaveAction> create(TLinkedList<TLinkableWrapper<InterleaveAction>> run) {
        ThreadIndexToElementList<InterleaveAction> result = new ThreadIndexToElementList<>();
        for (TLinkableWrapper<InterleaveAction> syncAction : run) {
            result.add(syncAction.element());
        }
        return result;
    }

    private void addActualRunWithLoops(TLinkedList<TLinkableWrapper<InterleaveAction>> run) {
        ThreadIndexToElementList<InterleaveAction> orig = create(run);

        if(alreadyProcessed(orig))  {
            return;
        }
        alreadyProcessed.add(wrap(orig));
        stillToBeProcessedAlternatingOrderContainer
                .add(new TLinkableWrapper<>(createAlternatingOrderContainer(orig,interleaveLoopContext)));
    }

    private boolean alreadyProcessed(ThreadIndexToElementList<InterleaveAction> newRun) {
        for(TLinkableWrapper<ThreadIndexToElementList<InterleaveAction>> element : alreadyProcessed) {
            if(isSame(element.element(),newRun)) {
                return true;
            }
        }
        return false;
    }

}
