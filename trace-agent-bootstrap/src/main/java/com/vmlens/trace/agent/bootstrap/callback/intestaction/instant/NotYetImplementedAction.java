package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;

public class NotYetImplementedAction extends AbstractInTestAction {

    private final int methodId;


    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();

    public NotYetImplementedAction(int methodId) {
        this.methodId = methodId;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        LoopWarningEvent warning = LoopWarningEvent.nonYetImplemented(methodId);
        threadLocalDataWhenInTest.runAdapter().sendMessage(warning,queueIn);
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
