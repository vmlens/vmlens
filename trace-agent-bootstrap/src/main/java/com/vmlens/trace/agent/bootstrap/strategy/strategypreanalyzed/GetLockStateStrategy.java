package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.GetLockStateEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class GetLockStateStrategy extends StrategyWithoutParam {
    
    private static final GetLockStateStrategy SINGLETON = new GetLockStateStrategy();
    
    @Override
    public void methodEnter(EnterExitContext context) {

    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<GetLockStateEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(new GetLockStateEvent(context.object()));

        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }
}
