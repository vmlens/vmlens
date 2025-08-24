package com.vmlens.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TestAllInterleavings {

    @Test
    public void whenFlagTrue_thenException() {
        // Given
        AllInterleavings allInterleavings = new AllInterleavings("test", true);

        // When
        RuntimeException actual = null;
        try{
            allInterleavings.hasNext();
        } catch(RuntimeException exp) {
            actual = exp;
        }

        // Then
        assertThat(actual,is(notNullValue()));
    }

    @Test
    public void whenDefaultConstructor_thenNoException() {
        // Given
        AllInterleavings allInterleavings = new AllInterleavings("test");

        // When
        RuntimeException actual = null;
        try{
            allInterleavings.hasNext();
        } catch(RuntimeException exp) {
            actual = exp;
        }

        // Then
        assertThat(actual,is(nullValue()));
    }

}
