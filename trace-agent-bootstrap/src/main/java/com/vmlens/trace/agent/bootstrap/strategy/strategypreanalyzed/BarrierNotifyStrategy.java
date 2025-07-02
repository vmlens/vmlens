package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barriertype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.barriertype.BarrierType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierEvent;

/**
 * methodExit after barrier notify event
 */

public class BarrierNotifyStrategy implements StrategyPreAnalyzed {

    private final BarrierType barrierType;
    private final BarrierKeyType barrierKeyType;

    public BarrierNotifyStrategy(BarrierType barrierType, BarrierKeyType barrierKeyType) {
        this.barrierType = barrierType;
        this.barrierKeyType = barrierKeyType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<BarrierEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(new BarrierEvent(barrierType,barrierKeyType,context.object()));

        context.threadLocalWhenInTestAdapter().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
