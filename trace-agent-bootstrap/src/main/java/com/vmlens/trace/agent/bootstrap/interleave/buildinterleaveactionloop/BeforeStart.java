package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveActionLoop;
import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class BeforeStart implements ProcessInterleaveAction {

    private final NormalizeContext normalizeContext;
    private final InterleaveAction start;

    public BeforeStart(NormalizeContext normalizeContext,
                       InterleaveAction start) {
        this.normalizeContext = normalizeContext;
        this.start = start;
    }

    @Override
    public ProcessInterleaveAction process(int index, InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        if(start.equalsNormalized(normalizeContext,array[index])) {
            result.add(wrap(new InterleaveActionLoop(start.threadIndex())));
            return new AfterStart(normalizeContext,start,index);
        }
        result.add(wrap(array[index]));
        return this;
    }

    @Override
    public void end(InterleaveAction[] array, TLinkedList<TLinkableWrapper<InterleaveAction>> result) {
        // Nothing to do
    }
}
