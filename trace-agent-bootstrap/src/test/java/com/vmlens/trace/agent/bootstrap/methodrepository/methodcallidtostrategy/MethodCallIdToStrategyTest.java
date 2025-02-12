package com.vmlens.trace.agent.bootstrap.methodrepository.methodcallidtostrategy;

public class MethodCallIdToStrategyTest {
/*
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
        StrategyAll strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(normalMethod);
        assertThat(strategy, is(NORMAL_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void synchronizedMethod() {
        MethodCallId synchronizedMethod = new MethodCallId("com/test/Test", "synchronized", "(I)V");
        methodCallIdToStrategyFromAnalyze.setMethodIsSynchronized(synchronizedMethod);
        StrategyAll strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(synchronizedMethod);
        assertThat(strategy, is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void threadRun() {
        MethodCallId threadRun = new MethodCallId("java/lang/Thread", "run", "()V");
        StrategyAll strategy = methodCallIdToStrategyDefaultValues.methodEnterStrategy(threadRun);
        assertThat(strategy, is(methodCallIdToStrategyDefaultValues.runMethodEnterStrategy()));
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

 */
}
