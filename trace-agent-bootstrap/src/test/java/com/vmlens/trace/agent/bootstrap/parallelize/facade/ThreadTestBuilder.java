package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.ThreadLocalWrapperMock;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public class ThreadTestBuilder {
    private long defaultObjectHashCode = 1L;
    private final ThreadIdToElementList<ActionForTest> actualRun;
    private final int threadIndex;
    private int position;

    public ThreadTestBuilder(ThreadIdToElementList<ActionForTest> actualRun, int threadIndex) {
        this.actualRun = actualRun;
        this.threadIndex = threadIndex;
    }
    public Position volatileAccess(final int fieldId,final int operation) {
      return create(new ActionForTest( new Position(threadIndex,position)){
          @Override
          public void execute(ThreadLocalWrapperMock[] loopThreadStateArray) {
              ParallelizeFacade.beforeFieldAccessVolatile(loopThreadStateArray[threadIndex()], defaultObjectHashCode, fieldId, operation);
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
