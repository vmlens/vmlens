package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class InterleaveActionWithPositionFactory {

    public final TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> runWithPosition =
            new TLinkedList<>();
    public final ThreadIndexToElementList<Position> run = new ThreadIndexToElementList<>();

    public Pair<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>,
            ThreadIndexToElementList<Position>> create(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun) {
        for (TLinkableWrapper<InterleaveAction> syncAction : actualRun) {
            add(syncAction.element());
        }
        return new ImmutablePair<>(runWithPosition, run);
    }

    private void add(BlockBuilderWithThreadIndex element) {
        int position = getPositionAtThreadIndex(element.threadIndex());
        add(new ElementAndPosition<BlockBuilder>(element, pos(element.threadIndex(), position)));
    }

    public void add(ElementAndPosition<BlockBuilder> withPosition) {
        run.add(withPosition.position());
        runWithPosition.add(new TLinkableWrapper(new ElementAndPosition(withPosition.element(), withPosition.position())));
    }

    public int getPositionAtThreadIndex(int threadIndex) {
        return run.getPositionAtThreadIndex(threadIndex);
    }

}
