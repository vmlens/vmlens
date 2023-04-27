package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import  com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestBuilderResult;

public class TestBuilderResultInterleaveFactory implements TestBuilderResult {
    private final ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun = new ThreadIdToElementList<>();

    @Override
    public void add(InterleaveAction interleaveAction, Position position) {
        actualRun.add(new InterleaveActionWithPositionFactoryImpl(interleaveAction,position.threadIndex));
    }

    @Override
    public void startThread(int index, Position position) {
        actualRun.add(new ThreadStartFactory(index,position.threadIndex));
    }

    public ThreadIdToElementList<InterleaveActionWithPositionFactory> actualRun() {
        return actualRun;
    }
}
