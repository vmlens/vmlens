package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionsInsideMethodStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class SetExecuteAfterOperation extends AbstractInTestAction {

    private final ExecuteAfterOperation runtimeEventAndSetInMethodIdAndPosition;
    private final FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy;

    public SetExecuteAfterOperation(ExecuteAfterOperation runtimeEventAndSetInMethodIdAndPosition,
                                    FilterActionsInsideMethodStrategy filterActionsInsideMethodStrategy) {
        this.runtimeEventAndSetInMethodIdAndPosition = runtimeEventAndSetInMethodIdAndPosition;
        this.filterActionsInsideMethodStrategy = filterActionsInsideMethodStrategy;
    }

    public SetExecuteAfterOperation(ExecuteAfterOperation runtimeEventAndSetInMethodIdAndPosition) {
        this(runtimeEventAndSetInMethodIdAndPosition,new WithoutFilterActions());

    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        threadLocalDataWhenInTest.setExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPosition);
    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return filterActionsInsideMethodStrategy.takeAction(threadLocalDataWhenInTest);
    }
}
