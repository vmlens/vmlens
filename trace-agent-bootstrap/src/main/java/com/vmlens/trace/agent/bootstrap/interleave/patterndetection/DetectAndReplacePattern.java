package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class DetectAndReplacePattern {

    public TLinkedList<TLinkableWrapper<InterleaveAction>> replace(ThreadIndexToElementList<InterleaveAction> orig) {
        TLinkedList<TLinkableWrapper<InterleaveAction>>  result = new TLinkedList<>();
        for(int i = 0 ; i <= orig.maxThreadIndex(); i++) {
            TLinkedList<TLinkableWrapper<InterleaveAction>> origList = orig.listAt(i);
            InterleaveAction[] array = toArray(InterleaveAction.class, origList);
            PatternKeyAndCount[] pattern = new DetectPattern(array).detect();
            InterleaveAction[] resultArray = new ReplacePattern(array, pattern).replace();
            for(InterleaveAction action :resultArray) {
                result.add(wrap(action));
            }
        }
        return result;
    }

}
