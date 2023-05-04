package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.ResultTestBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class BlockBuilderTestBuilderResult implements ResultTestBuilder {

    @Override
    public void joinThread(int index, Position position) {
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(new ThreadJoin(index),position)));
    }

    private final TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList =
            new TLinkedList<>();

    @Override
    public void add(InterleaveAction interleaveAction, Position position) {
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(interleaveAction,position)));
    }

    @Override
    public void startThread(int index, Position position) {
        blockBuilderList.add(new TLinkableWrapper<ElementAndPosition<BlockBuilder>>
                (new ElementAndPosition(new ThreadStart(index),position)));
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList() {
        return blockBuilderList;
    }
}
