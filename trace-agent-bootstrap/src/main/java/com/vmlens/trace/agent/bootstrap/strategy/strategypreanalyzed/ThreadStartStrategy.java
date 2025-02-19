package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunStartAtomicActionWithNewThread;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetEndAtomicActionForPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setinmethodidandposition.RuntimeEventAndSetInMethodIdAndPositionImpl;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartStrategy implements StrategyPreAnalyzed {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadStartStrategy();

    private ThreadStartStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        context.threadLocalWhenInTestAdapter().process(
                new RunStartAtomicActionWithNewThread(new RunnableOrThreadWrapper(context.object())));
    }

    @Override
    public void methodExit(EnterExitContext context) {
        RuntimeEventAndSetInMethodIdAndPositionImpl<ThreadStartEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new RuntimeEventAndSetInMethodIdAndPositionImpl<>(new ThreadStartEvent());

        context.threadLocalWhenInTestAdapter().process(
                new SetEndAtomicActionForPreAnalyzed(runtimeEventAndSetInMethodIdAndPositionImpl));
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }
}
