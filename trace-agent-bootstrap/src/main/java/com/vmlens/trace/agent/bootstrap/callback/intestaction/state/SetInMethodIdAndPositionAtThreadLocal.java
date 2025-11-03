package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

/**
 * Used for pre analyzed methods to set the in method id for the runtime action
 */
public class SetInMethodIdAndPositionAtThreadLocal extends AbstractInTestAction {

    private final InMethodIdAndPosition inMethodIdAndPosition;
    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy = new WithoutFilterActions();

    public SetInMethodIdAndPositionAtThreadLocal(InMethodIdAndPosition inMethodIdAndPosition) {
        this.inMethodIdAndPosition = inMethodIdAndPosition;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        threadLocalDataWhenInTest.setInMethodIdAndPosition(inMethodIdAndPosition);
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
