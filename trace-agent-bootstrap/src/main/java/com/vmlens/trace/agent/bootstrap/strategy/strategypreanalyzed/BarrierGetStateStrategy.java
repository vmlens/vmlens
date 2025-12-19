package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierGetStateEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class BarrierGetStateStrategy extends StrategyWithoutParam {

    private final BarrierKeyType barrierKeyType;

    public BarrierGetStateStrategy(BarrierKeyType barrierKeyType) {
        this.barrierKeyType = barrierKeyType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {

    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<BarrierGetStateEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(new BarrierGetStateEvent(barrierKeyType,context.object()));

        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }
}
