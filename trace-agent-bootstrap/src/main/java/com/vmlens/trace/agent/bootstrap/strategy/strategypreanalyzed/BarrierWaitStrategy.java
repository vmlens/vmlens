package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfterLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.AtomicBegin;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.AtomicEnd;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierWaitEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierWaitExitEvent;


/**
 *  methodEnter call waitCallOrBeforeLockExit with event
 *  methodExit call  afterLockExitOrWait
 *
 */

public class BarrierWaitStrategy implements StrategyPreAnalyzed {
    

    private final BarrierKeyType barrierKeyType;

    public BarrierWaitStrategy(BarrierKeyType barrierKeyType) {
        this.barrierKeyType = barrierKeyType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        BarrierWaitEnterEvent event = new BarrierWaitEnterEvent(barrierKeyType);
        RunBeforeLockExitOrWait<BarrierWaitEnterEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicBegin());
        context.threadLocalWhenInTestAdapter().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        BarrierWaitExitEvent event = new BarrierWaitExitEvent(barrierKeyType);
        RunBeforeLockExitOrWait<BarrierWaitExitEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicEnd());
        context.threadLocalWhenInTestAdapter().process(action);
        context.threadLocalWhenInTestAdapter().process(new RunAfterLockExitOrWait());
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
