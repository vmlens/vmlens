package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.ThreadIndexContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRunOld;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionOld.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionOld.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.l;

/**
 * main entry point
 * responsible for loop detection, second run
 * ToDo needs to be synchronized, vielleicht auftrennen also commands + liste unter einem lock?
 */
public class InterleaveFacade {

    private final InterleaveContainer interleaveContainer;
    private final Logger logger;

    private TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun = null;
    private CalculatedRunOld calculatedRun = new CalculatedRunOld();

    // For easier mocking
    public InterleaveFacade(InterleaveContainer interleaveContainer, Logger logger) {
        this.interleaveContainer = interleaveContainer;
        this.logger = logger;
    }

    public InterleaveFacade(Logger logger) {
        this(new InterleaveContainer(logger), logger);
    }

    public boolean needsToWait(int threadIndex, ThreadIndexContainer threadIndexContainer) {
        return calculatedRun.needsToWait(threadIndex, threadIndexContainer);
    }

    public void afterSyncAction(Position position, boolean moreThanOneThread, SyncAction syncAction) {
        if (moreThanOneThread) {
            calculatedRun.advance();
        }
        actualRun.add(l(new SyncActionAndPosition(position, moreThanOneThread, syncAction)));
    }

    public boolean advance() {
        // First run -> return true and create first actualRun
        if (actualRun == null) {
            actualRun = new TLinkedList<TLinkableWrapper<SyncActionAndPosition>>();
            return true;
        }
        interleaveContainer.addActualRun(actualRun);
        actualRun = new TLinkedList<TLinkableWrapper<SyncActionAndPosition>>();
        if (interleaveContainer.hasNext()) {
            calculatedRun = interleaveContainer.next();
            return true;
        }
        return false;
    }
}
