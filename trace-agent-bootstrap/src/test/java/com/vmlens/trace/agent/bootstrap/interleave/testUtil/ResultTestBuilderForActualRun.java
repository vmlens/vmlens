package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadJoinFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ResultTestBuilderForActualRun extends AbstractResultTestBuilderForInterleave {

    private final ThreadIndexToElementList<InterleaveActionWithPositionFactory> threadIndexToFactoryList = new ThreadIndexToElementList<>();
    private TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> factoryList =
            new TLinkedList<>();
    private ThreadIndexToElementList<Position> positions = new ThreadIndexToElementList<Position>();

    @Override
    protected void add(InterleaveAction interleaveAction, Position position) {
        add(new InterleaveActionWithPositionFactoryImpl(interleaveAction, position.threadIndex), position);
    }

    @Override
    public void startThread(int index, Position position) {
        add(new ThreadStartFactory(position.threadIndex, index), position);
    }

    @Override
    public void joinThread(int index, Position position) {
        add(new ThreadJoinFactory(position.threadIndex, index), position);
    }


    private void add(InterleaveActionWithPositionFactory interleaveActionWithPositionFactory, Position position) {
        factoryList.add(new TLinkableWrapper<>(interleaveActionWithPositionFactory));
        threadIndexToFactoryList.add(interleaveActionWithPositionFactory);
        positions.add(position);
    }

    public ThreadIndexToElementList<Position> positions() {
        return positions;
    }

    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> factoryList() {
        return factoryList;
    }

    public ThreadIndexToElementList<InterleaveActionWithPositionFactory> threadIndexToFactoryList() {
        return threadIndexToFactoryList;
    }
}
