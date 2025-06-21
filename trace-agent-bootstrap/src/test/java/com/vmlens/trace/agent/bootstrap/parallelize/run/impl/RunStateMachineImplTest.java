package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateMachineHelper.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 *  Tests starting of a new thread, e.g. wait till the started
 *  Two variations:
 *         processNewTestTask, thread start or
 *         thread start, processNewTestTask
 *  thread is active
 *  Tests thread join
 *  Tests blocking. e.g, that still only one thread is active
 */
public class RunStateMachineImplTest {

    /**
     *
     * State Active
     * Event newTestTaskStarted
     * Event processNewTestTask
     *
     */
    @Test
    public void processNewTestTask() {
        // Given
        ActualRun actualRun = new ActualRun();
        InterleaveRunWithoutCalculated interleaveRunWithoutCalculated
                = new InterleaveRunWithoutCalculated(actualRun);
        RunStateMachineHelper runStateMachineWrapper = new RunStateMachineHelper(interleaveRunWithoutCalculated);

        ThreadWrapper threadWrapper = createThreadWrapper();

        // When newTestTaskStarted
        runStateMachineWrapper.runStateMachineImpl().newTestTaskStarted(threadWrapper);

        // Then
        assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(threadIndex(0),
                runStateMachineWrapper.sendEvent()),is(false));
        assertThat(runStateMachineWrapper.runStateMachineImpl().checkStopWaiting(runStateMachineWrapper.sendEvent()),
              is(false));

        //  When processNewTestTask
        ThreadLocalForParallelize threadLocalForParallelize = createThreadLocalForParallelize(ThreadState.BLOCKED);

        NewTaskContext newTaskContext = new NewTaskContext(runStateMachineWrapper.queueInMock(),threadWrapper,threadLocalForParallelize);
        runStateMachineWrapper.runStateMachineImpl().processNewTestTask(newTaskContext,
                runStateMachineWrapper.run(),runStateMachineWrapper.sendEvent());

        // Then
        assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(threadIndex(0),
                runStateMachineWrapper.sendEvent()),is(true));

        assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(threadIndex(1),
                runStateMachineWrapper.sendEvent()),is(true));
    }


}
