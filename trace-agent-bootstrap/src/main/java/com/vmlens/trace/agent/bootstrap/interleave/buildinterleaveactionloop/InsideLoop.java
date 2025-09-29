package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class InsideLoop implements ProcessInterleaveAction {

    private final InterleaveAction start;
    private int startLastSeen;

    public InsideLoop(InterleaveAction start,
                      int startLastSeen) {
        this.start = start;
        this.startLastSeen = startLastSeen;
    }

    @Override
    public ProcessInterleaveAction process(int index, InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        if(start.equalsNormalized(array[index])) {
            startLastSeen = index;
        }
        if(start.threadIndex() != array[index].threadIndex()) {
            result.add(wrap(array[index]));
            return new AfterLoop();
        }
        return this;
    }

    @Override
    public void end(InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        for(int i =  startLastSeen ; i < array.length; i++) {
            result.add(wrap(array[i]));
        }
    }
}
