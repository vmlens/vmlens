package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetFields;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;

public class RunBeforeLockExitOrWait<EVENT extends ExecuteBeforeEvent> extends AbstractInTestAction {

    private final EVENT runtimeEvent;
    private final SetFields<EVENT> setFieldsStrategy;
    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy;

    public RunBeforeLockExitOrWait(EVENT runtimeEvent, SetFields<EVENT> setFieldsStrategy,
                                   FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy) {
        this.runtimeEvent = runtimeEvent;
        this.setFieldsStrategy = setFieldsStrategy;
        this.filterActionsInsideMethodStrategy = filterActionsInsideMethodStrategy;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        setFieldsStrategy.setFields(runtimeEvent,threadLocalDataWhenInTest);
        threadLocalDataWhenInTest.runAdapter().beforeLockExitWaitOrThreadStart(runtimeEvent,threadLocalDataWhenInTest,queueIn);
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
