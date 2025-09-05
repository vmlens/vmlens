package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * currently thread start and thread join
 */

public interface IndependentBlock {
    void addFixedOrder(Position myPosition,
                       TLinkedList<TLinkableWrapper<LeftBeforeRight>> orderArraysBuilder,
                       ThreadIndexToMaxPosition threadIndexToMaxPosition);



}
