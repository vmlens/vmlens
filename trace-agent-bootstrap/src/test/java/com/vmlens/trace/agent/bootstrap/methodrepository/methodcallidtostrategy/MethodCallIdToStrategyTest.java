package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

import com.vmlens.trace.agent.bootstrap.methodrepository.MethodCallId;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterStrategy;
import org.junit.Before;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyDefaultValues.*;
import static com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.NORMAL_METHOD_ENTER_STRATEGY;
import static com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.SYNCHRONIZED_METHOD_ENTER_STRATEGY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MethodCallIdToStrategyTest {

    private MethodCallIdToStrategyDefaultValues methodCallIdToStrategyDefaultValues;
    private MethodCallIdToStrategyFromAnalyze methodCallIdToStrategyFromAnalyze;

    @Before
    public void initialize() {
        methodCallIdToStrategyFromAnalyze = new MethodCallIdToStrategyFromAnalyze();
        methodCallIdToStrategyDefaultValues = new MethodCallIdToStrategyDefaultValues(
                new MethodCallIdToStrategyFromResource(), methodCallIdToStrategyFromAnalyze);
    }

    @Test
    public void normalMethod() {
        MethodCallId normalMethod = new MethodCallId("com/test/Test", "normal", "(I)V");
        MethodEnterStrategy strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(normalMethod);
        assertThat(strategy, is(NORMAL_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void synchronizedMethod() {
        MethodCallId synchronizedMethod = new MethodCallId("com/test/Test", "synchronized", "(I)V");
        methodCallIdToStrategyFromAnalyze.setMethodIsSynchronized(synchronizedMethod);
        MethodEnterStrategy strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(synchronizedMethod);
        assertThat(strategy, is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void threadRun() {
        MethodCallId threadRun = new MethodCallId("java/lang/Thread", "run", "()V");
        MethodEnterStrategy strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(threadRun);
        assertThat(strategy, is(RUN_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void threadStart() {
        MethodCallId threadRun = new MethodCallId("java/lang/Thread", "start", "()V");
        BeforeMethodCallStrategy strategy = methodCallIdToStrategyDefaultValues.beforeMethodCallStrategy(threadRun);
        assertThat(strategy, is(BEFORE_THREAD_START));
    }

    @Test
    public void beforeNormalMethod() {
        MethodCallId normalMethod = new MethodCallId("com/test/Test", "normal", "(I)V");
        BeforeMethodCallStrategy strategy = methodCallIdToStrategyDefaultValues.beforeMethodCallStrategy(normalMethod);
        assertThat(strategy, is(BEFORE_NO_OP));
    }
}
