package com.vmlens.inttest.automatictest.failure;

import com.vmlens.api.testbuilder.AtomicTestBuilder;
import com.vmlens.inttest.ABAStack;
import com.vmlens.inttest.atomic.NonAtomicCounter;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestABAStack {

    @Test
    public void testStack() throws InterruptedException {
        RuntimeException actualException = null;
        try {
            new AtomicTestBuilder<>(ABAStack::new)
                    .addWrite((stack) -> stack.push(4))
                    .addUpdate(ABAStack::pop)
                    .runTests();
        }
        catch (RuntimeException exp) {
            actualException = exp;
        }
        assertThat(actualException, instanceOf(RuntimeException.class));
    }

}
