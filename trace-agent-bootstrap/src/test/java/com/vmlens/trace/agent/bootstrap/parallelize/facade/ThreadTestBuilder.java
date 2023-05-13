package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;

public class ThreadTestBuilder {
    private final ThreadIndexToElementList<ActionForTest> actualRun;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ThreadIndexToElementList<ActionForTest> actualRun, int threadIndex) {
        this.actualRun = actualRun;
        this.threadIndex = threadIndex;
    }

    public Position volatileAccess(final int fieldId, final int operation) {
        return create(new ActionForTest(new Position(threadIndex, position)) {
            @Override
            public void execute(ThreadLocalWrapperMock[] loopThreadStateArray) {
                ParallelizeFacade.afterFieldAccessVolatile(loopThreadStateArray[threadIndex()], fieldId, operation);
            }
        });
    }
    public Position startThread(final int startedThreadIndex) {
        return create(new ActionForTest( new Position(threadIndex,position)){
            @Override
            public void execute(ThreadLocalWrapperMock[] loopThreadStateArray) {
                ParallelizeFacade.beforeThreadStart(loopThreadStateArray[threadIndex()],
                        new RunnableOrThreadWrapper(loopThreadStateArray[startedThreadIndex]));
                ParallelizeFacade.beginThreadMethodEnter(loopThreadStateArray[startedThreadIndex],
                        new RunnableOrThreadWrapper(loopThreadStateArray[startedThreadIndex]));
            }

        });
    }
    private Position create(ActionForTest actionForTest) {
        actualRun.add(actionForTest);
        position++;
        return actionForTest.position();
    }
}
