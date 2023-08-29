package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.ResultTestBuilder;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeFacadeImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;

public class ResultTestBuilderForParallelize implements ResultTestBuilder {

    private final ThreadIndexToElementList<ActionForTest> actualRun =
            new ThreadIndexToElementList<ActionForTest>();

    @Override
    public void volatileAccess(final int fieldId, final int operation, Position position) {
        actualRun.add(new ActionForTest(position) {
            @Override
            public void execute(ParallelizeFacadeImpl parallelizeFacadeImpl, ThreadLocalWrapperMock[] loopThreadStateArray) {
                parallelizeFacadeImpl.afterFieldAccessVolatile(loopThreadStateArray[threadIndex()], fieldId, operation);
            }
        });
    }

    @Override
    public void startThread(final int index, Position temp) {
        actualRun.add(new ActionForTest(temp) {
            @Override
            public void execute(ParallelizeFacadeImpl parallelizeFacadeImpl, ThreadLocalWrapperMock[] loopThreadStateArray) {
                parallelizeFacadeImpl.beforeThreadStart(loopThreadStateArray[threadIndex()],
                        new RunnableOrThreadWrapper(loopThreadStateArray[index]));
                parallelizeFacadeImpl.afterThreadStart(loopThreadStateArray[threadIndex()]);
                parallelizeFacadeImpl.beginThreadMethodEnter(loopThreadStateArray[index],
                        new RunnableOrThreadWrapper(loopThreadStateArray[index]));
            }
        });
    }

    @Override
    public void joinThread(int index, Position temp) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public void monitorEnter(int id, Position temp) {
        throw new RuntimeException("not implemented yet");
    }

    @Override
    public void monitorExit(int id, Position temp) {
        throw new RuntimeException("not implemented yet");
    }

    public ThreadIndexToElementList<ActionForTest> actualRun() {
        return actualRun;
    }
}
