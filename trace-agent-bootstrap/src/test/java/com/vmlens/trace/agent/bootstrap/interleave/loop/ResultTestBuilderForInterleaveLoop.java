package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.AbstractResultTestBuilderForInterleave;

public class ResultTestBuilderForInterleaveLoop extends AbstractResultTestBuilderForInterleave {
    private final ThreadIndexToElementList<InterleaveActionWithPositionFactory> actualRun = new ThreadIndexToElementList<>();

    @Override
    public void add(InterleaveAction interleaveAction, Position position) {
        actualRun.add(new InterleaveActionWithPositionFactoryImpl(interleaveAction, position.threadIndex));
    }

    @Override
    public void startThread(int index, Position position) {
        actualRun.add(new ThreadStartFactory(index, position.threadIndex));
    }

    @Override
    public void joinThread(int index, Position temp) {
        // ToDo implement
    }

    public ThreadIndexToElementList<InterleaveActionWithPositionFactory> actualRun() {
        return actualRun;
    }
}
