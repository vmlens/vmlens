package com.vmlens.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class TestRunner {

    @Test
    public void testException() {
        // Given
        final RuntimeException exception = new RuntimeException();

        // When
        RuntimeException actual = null;
        try{
            Runner.runParallel(() -> {throw exception;});
        } catch(RuntimeException exp) {
            actual = exp;
        }

        // Then
        assertThat(actual,is(exception));
    }

}
