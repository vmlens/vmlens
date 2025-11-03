package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;

public class ThreadJoinByThreadPool extends AbstractInTestAction {

    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();
    private final Object taskOrPool;

    public ThreadJoinByThreadPool(Object taskOrPool) {
        this.taskOrPool = taskOrPool;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().threadJoinedByPool(new JoinAction(taskOrPool,queueIn,
                threadLocalDataWhenInTest));
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
