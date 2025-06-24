package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import org.junit.Test;

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
        ActualRun actualRun = new ActualRun();
        InterleaveRunWithoutCalculated interleaveRunWithoutCalculated
                = new InterleaveRunWithoutCalculated(actualRun);
        RunStateMachineHelper runStateMachineWrapper = new RunStateMachineHelper(interleaveRunWithoutCalculated);

        // When

        // Then

    }

}
