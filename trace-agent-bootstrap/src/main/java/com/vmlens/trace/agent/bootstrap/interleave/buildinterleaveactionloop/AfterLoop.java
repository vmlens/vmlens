package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class AfterLoop implements ProcessInterleaveAction {
    @Override
    public ProcessInterleaveAction process(int index, InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        result.add(wrap(array[index]));
        return this;
    }

    @Override
    public void end(InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {

    }
}
