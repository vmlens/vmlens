package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.barrierkeytype.BarrierKeyType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.AtomicBegin;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.AtomicEnd;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierWaitEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.BarrierWaitExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;


/**
 *  methodEnter call waitCallOrBeforeLockExit with event
 *  methodExit call  afterLockExitOrWait
 *
 */

public class BarrierWaitStrategy extends StrategyWithoutParam {
    

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
        context.inTestActionProcessor().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        BarrierWaitExitEvent event = new BarrierWaitExitEvent(barrierKeyType);
        RunBeforeLockExitOrWait<BarrierWaitExitEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicEnd());
        context.inTestActionProcessor().process(action);
        context.inTestActionProcessor().process(new RunAfterLockExitWaitOrThreadStart());
    }

}
