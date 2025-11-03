package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class OnAfterMethodCall extends AbstractInTestAction {

    private final int inMethodId;
    private final int position;
    private final ReadWriteLockMap readWriteLockMap;
    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();

    public OnAfterMethodCall(int inMethodId,
                             int position,
                             ReadWriteLockMap readWriteLockMap) {
        this.inMethodId = inMethodId;
        this.position = position;
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        if (threadLocalDataWhenInTest.executeAfterOperation() == null) {
            return;
        }

        threadLocalDataWhenInTest.executeAfterOperation().execute(inMethodId, position, threadLocalDataWhenInTest,queueIn,
                readWriteLockMap);
        threadLocalDataWhenInTest.setExecuteAfterOperation(null);
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
