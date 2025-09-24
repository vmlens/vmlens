package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;

public class InterleaveActionLoopFactory {

    public TLinkedList<TLinkableWrapper<InterleaveAction>> create(TLinkedList<TLinkableWrapper<InterleaveAction>> original) {
        NormalizeContext normalizeContext = new NormalizeContext();
        InterleaveAction[] array = toArray(InterleaveAction.class, original);
        Integer startInteger = new FindStartPosition(normalizeContext).findStart(array);
        if(startInteger == null) {
            return original;
        }

        TLinkedList<TLinkableWrapper<InterleaveAction>> result = new TLinkedList<>();
        ProcessInterleaveAction process = new BeforeStart(normalizeContext,array[startInteger]);
        for( int index = 0 ; index < array.length; index++ ) {
            process = process.process(index, array, result);
        }
        process.end(array, result);
        return result;
    }

}
