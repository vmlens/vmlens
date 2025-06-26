package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierEvent;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierKeyTypeFuture;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierTypeWait;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * beforeWait go to State BarrierWait
 * after  go To State BarrierNotified
 * afterWait  go to State Active
 *
 * Variations:
 *      blocking
 *      timeout
 *
 *  notify, wait can actually not happen
 *  wait should not be executed through the program logic
 *
 */
public class BarrierOrConditionTest {

    /**
     * before wait
     * we can execute the event here
     *          notify (when threads wait)
     */
    @Test
    public void waitNotifyHappyFlow() {
        // Given
        BarrierEvent wait = new BarrierEvent(BarrierTypeWait.SINGLETON, BarrierKeyTypeFuture.SINGLETON, new Object());

        ActualRun actualRun = new ActualRun();
        InterleaveRunWithoutCalculated interleaveRunWithoutCalculated
                = new InterleaveRunWithoutCalculated(actualRun);
        RunStateMachineHelper runStateMachineWrapper = new RunStateMachineHelper(interleaveRunWithoutCalculated);
        ThreadForParallelize threadForParallelize = mock(ThreadForParallelize.class);
        when(threadForParallelize.isBlocked()).thenReturn(ThreadState.ACTIVE);
        ThreadLocalForParallelize threadLocal = new ThreadLocalForParallelize(threadForParallelize);
        ThreadLocalWhenInTest newThread = runStateMachineWrapper.startThread(threadLocal);

        wait.setThreadIndex(newThread.threadIndex());
        // When
        runStateMachineWrapper.runStateMachineImpl().beforeLockExitOrWait(wait,newThread, runStateMachineWrapper.sendEvent());

        // Then
         assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(runStateMachineWrapper.mainThread(),
                 runStateMachineWrapper.sendEvent()),is(false));

         // When
        when(threadForParallelize.isBlocked()).thenReturn(ThreadState.BLOCKED);

        // Then
        assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(runStateMachineWrapper.mainThread(),
                runStateMachineWrapper.sendEvent()),is(true));

    }

}
