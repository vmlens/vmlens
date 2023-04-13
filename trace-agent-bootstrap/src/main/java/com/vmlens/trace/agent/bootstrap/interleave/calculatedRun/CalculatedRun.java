package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * @rule schould only return active for one threadid at a given time
 * @potential filter for loops detection if the actual run conforms to the calculated run
 *
 * ToDo Loop Detection is independent from the same for intitial and calculated run from order
 * wrapper for after
 *
 *
 */
public interface CalculatedRun {
    boolean isActive(int threadId);
    //void after(InterleaveActionWithPosition interleaveActionWithPosition);
    TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun();
    void after(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory);
}
