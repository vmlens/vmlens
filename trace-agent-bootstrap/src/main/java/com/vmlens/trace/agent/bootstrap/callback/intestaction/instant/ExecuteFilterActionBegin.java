package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionBegin;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class ExecuteFilterActionBegin implements InTestAction {
    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        // No Op
        // only to start filter action
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return new FilterActionBegin().takeAction(threadLocalDataWhenInTest);
    }
}
