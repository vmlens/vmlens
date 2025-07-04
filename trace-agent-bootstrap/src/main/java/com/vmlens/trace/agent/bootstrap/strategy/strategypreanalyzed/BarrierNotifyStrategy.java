package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfterLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierNotifyEvent;

/**
 * methodExit after barrier notify event
 */

public class BarrierNotifyStrategy implements StrategyPreAnalyzed {

    private final BarrierKeyType barrierKeyType;

    public BarrierNotifyStrategy(BarrierKeyType barrierKeyType) {
        this.barrierKeyType = barrierKeyType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        /**
         * notify is similar to monitor exit
         * we must send the event before the actual notification
         *
         */
        BarrierNotifyEvent event = new BarrierNotifyEvent(barrierKeyType);
        RunBeforeLockExitOrWait<BarrierNotifyEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new WithoutAtomic());

        context.threadLocalWhenInTestAdapter().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        context.threadLocalWhenInTestAdapter().process(new RunAfterLockExitOrWait());
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
