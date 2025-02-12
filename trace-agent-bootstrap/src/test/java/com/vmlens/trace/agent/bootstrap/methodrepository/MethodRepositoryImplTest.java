package com.vmlens.trace.agent.bootstrap.methodrepository;

public class MethodRepositoryImplTest {

    private final MethodCallId SYNCHRONIZED_METHOD = new MethodCallId("test", "synchronized", "");
/*
    @Test
    public void analyzeTransformUse() {
        MethodRepositoryImpl methodRepository = new MethodRepositoryImpl(checkIsThreadRun);
        methodRepository.setMethodIsSynchronized(SYNCHRONIZED_METHOD);
        int id = methodRepository.asInt(SYNCHRONIZED_METHOD);
        assertThat(methodRepository.methodEnterStrategy(id), is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void transformAnalyzeUse() {
        MethodRepositoryImpl methodRepository = new MethodRepositoryImpl(checkIsThreadRun);
        int id = methodRepository.asInt(SYNCHRONIZED_METHOD);
        methodRepository.setMethodIsSynchronized(SYNCHRONIZED_METHOD);
        assertThat(methodRepository.methodEnterStrategy(id), is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }
*/
}
