package com.vmlens.trace.agent.bootstrap;

import com.vmlens.api.AllInterleavings;
import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.trace.agent.bootstrap.callback.MethodCallback;
import com.vmlens.trace.agent.bootstrap.callback.PreAnalyzedCallback;
import com.vmlens.trace.agent.bootstrap.callback.VmlensApiCallback;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositorySingleton;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.NormalMethodStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadJoinStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.ThreadStartStrategy;
import org.junit.Test;

public class TestNonBlockingAtomic {

    @Test
    public void testConcurrentSkipListMap() throws Throwable {
        MethodRepositorySingleton.INSTANCE.setStrategyPreAnalyzed(1, ThreadStartStrategy.SINGLETON);
        MethodRepositorySingleton.INSTANCE.setStrategyPreAnalyzed(2, ThreadJoinStrategy.SINGLETON);
        MethodRepositorySingleton.INSTANCE.setStrategyAll(3, NormalMethodStrategy.SINGLETON);
        CheckIsThreadRun.SINGLETON.isThreadRun();

        try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withMaximumAlternatingOrders(4)
                .withMaximumIterations(8)
                .build("TestNonBlockingAtomic.testConcurrentSkipListMap")) {
            while (allInterleavings.hasNext()) {
                AllInterleavings underTest = new AllInterleavings("underTest");
                while (VmlensApiCallback.hasNext(underTest)) {
                    Thread first = new Thread() {
                        @Override
                        public void run() {
                            MethodCallback.methodEnter(this, 3);
                            MethodCallback.methodExit(this, 3);
                        }
                    };

                    MethodCallback.beforeMethodCall(3, 1, 1);
                    PreAnalyzedCallback.methodEnter(first, 1);
                    first.start();
                    PreAnalyzedCallback.methodExit(first, 1);

                    MethodCallback.afterMethodCall(3, 1, 1);

                    PreAnalyzedCallback.methodEnter(first, 2);
                    first.join();
                    PreAnalyzedCallback.methodExit(first, 2);
                }
            }
        }
    }

}
