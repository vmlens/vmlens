package com.vmlens.trace.agent.bootstrap.methodidtostrategy;

import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.methodidtostrategy.methodcallidtostrategy.MethodCallIdToStrategyFromAnalyze.SYNCHRONIZED_METHOD_ENTER_STRATEGY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MethodRepositoryTest {

    private final MethodCallId SYNCHRONIZED_METHOD = new MethodCallId("test", "synchronized", "");

    @Test
    public void analyzeTransformUse() {
        MethodRepository methodRepository = new MethodRepository();
        methodRepository.setMethodIsSynchronized(SYNCHRONIZED_METHOD);
        int id = methodRepository.asInt(SYNCHRONIZED_METHOD);
        assertThat(methodRepository.methodEnterStrategy(id), is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }

    @Test
    public void transformAnalyzeUse() {
        MethodRepository methodRepository = new MethodRepository();
        int id = methodRepository.asInt(SYNCHRONIZED_METHOD);
        methodRepository.setMethodIsSynchronized(SYNCHRONIZED_METHOD);
        assertThat(methodRepository.methodEnterStrategy(id), is(SYNCHRONIZED_METHOD_ENTER_STRATEGY));
    }

}
