package com.vmlens.inttest.structuredconcurrency;

import com.vmlens.api.AllInterleavings;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.StructuredTaskScope;

import static com.vmlens.api.Runner.runParallel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestStructuredConcurrency {

    private volatile int j = 0;

    @Disabled
    @Test
    public void testStructuredConcurrency() throws InterruptedException {
        Set<Integer> expectedSet = new HashSet<>();
        expectedSet.add(1);
        expectedSet.add(2);
        Set<Integer> countSet = new HashSet<>();
        try(AllInterleavings allInterleavings = new AllInterleavings("testStructuredConcurrency")) {
            while (allInterleavings.hasNext()) {
                j = 0;
                try (var scope =  StructuredTaskScope.open()) {
                    StructuredTaskScope.Subtask<Integer> subtask1 = scope.fork( () -> j++ );
                    StructuredTaskScope.Subtask<Integer> subtask2 = scope.fork( () -> j++ );
                    scope.join();
                    countSet.add(subtask1.get());
                    countSet.add(subtask2.get());
                }
            }
            assertThat(countSet,is(expectedSet));
        }
    }


}
