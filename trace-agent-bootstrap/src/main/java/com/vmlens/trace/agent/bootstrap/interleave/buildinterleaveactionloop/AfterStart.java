package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class AfterStart implements ProcessInterleaveAction {

    private final NormalizeContext normalizeContext;
    private final InterleaveAction start;
    private int startLastSeen;

    public AfterStart(NormalizeContext normalizeContext,
                      InterleaveAction start,
                      int startLastSeen) {
        this.normalizeContext = normalizeContext;
        this.start = start;
        this.startLastSeen = startLastSeen;
    }

    @Override
    public ProcessInterleaveAction process(int index, InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        if(start.equalsNormalized(normalizeContext,array[index])) {
            startLastSeen = index;
        }
        return this;
    }

    @Override
    public void end(InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        for(int i =  startLastSeen; i < array.length; i++) {
            result.add(wrap(array[i]));
        }
    }
}
