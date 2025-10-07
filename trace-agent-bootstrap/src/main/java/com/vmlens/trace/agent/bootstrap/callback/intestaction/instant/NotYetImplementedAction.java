package com.vmlens.trace.agent.bootstrap.callback.intestaction.instant;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AbstractInTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;

public class NotYetImplementedAction extends AbstractInTestAction {

    private final int methodId;


    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

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
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }
}
