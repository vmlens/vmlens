package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class RunAfterLockExitWaitOrThreadStart implements InTestAction {

    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().afterLockExitWaitOrThreadStart(threadLocalDataWhenInTest,queueIn);
    }


    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }

}
