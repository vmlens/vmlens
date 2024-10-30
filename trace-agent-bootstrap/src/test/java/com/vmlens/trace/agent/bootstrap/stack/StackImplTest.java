package com.vmlens.trace.agent.bootstrap.stack;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class StackImplTest {

    @Test
    public void pushAndPop() {
        // Given
        String first = "first";
        String second = "second";
        StackImpl<String> stack = new StackImpl<>();

        // When
        stack.push(first);
        stack.push(second);

        // Then
        assertThat(stack.pop(), is(second));
        assertThat(stack.pop(), is(first));
        assertThat(stack.pop(), is(nullValue()));
    }

}
