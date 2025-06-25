package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.lock.LockTypes.WRITE_LOCK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Lock exit is special:
 *      beforeLockExit(Event)
 *              Send Event
 *      after(thread index)
 *              next step in actual run
 *              without send event
 *
 *
 *  to solve the follwoing scenario
 *
 *  monitor enter
 *                  try monitor enter (blocked)
 *  monitor exit allowed
 *          execute both add to actual run + send event
 *  after(thread index)
 */

public class LockTest {

    @Test
    public void lockExit() {
        // Given
        LockExitEvent wait = new LockExitEvent(WRITE_LOCK,new Object());

        ActualRun actualRun = new ActualRun();
        InterleaveRunWithoutCalculated interleaveRunWithoutCalculated
                = new InterleaveRunWithoutCalculated(actualRun);
        RunStateMachineHelper runStateMachineWrapper = new RunStateMachineHelper(interleaveRunWithoutCalculated);
        ThreadLocalWhenInTest newThread = runStateMachineWrapper.startThread(ThreadState.ACTIVE);

        // When
        runStateMachineWrapper.runStateMachineImpl().beforeLockExitOrWait(wait,newThread,runStateMachineWrapper.sendEvent());

        // Then
        assertThat(runStateMachineWrapper.runStateMachineImpl().isActive(runStateMachineWrapper.mainThread(),
                runStateMachineWrapper.sendEvent()),is(true));

    }
}
