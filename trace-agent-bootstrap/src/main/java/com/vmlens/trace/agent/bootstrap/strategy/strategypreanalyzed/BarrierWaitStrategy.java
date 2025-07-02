package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barriertype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfterLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierEvent;


/**
 *  methodEnter call waitCallOrBeforeLockExit with event
 *  methodExit call  afterLockExitOrWait
 *
 */

public class BarrierWaitStrategy implements StrategyPreAnalyzed {
    
    private final BarrierType barrierType;
    private final BarrierKeyType barrierKeyType;

    public BarrierWaitStrategy(BarrierType barrierType, BarrierKeyType barrierKeyType) {
        this.barrierType = barrierType;
        this.barrierKeyType = barrierKeyType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        BarrierEvent event = new BarrierEvent(barrierType,barrierKeyType,context.object());
        RunBeforeLockExitOrWait<BarrierEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdAndPosition<>(context.readWriteLockMap()), new WithoutAtomic());
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
