package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ElementsPerThreadTest {

    @Test
    public void testAllMethods() {
        // Given
        String first = "FIRST";
        String second = "SECOND";
        String third = "THIRD";
        String[] array = new String[3];
        array[0] = first;
        array[1] = second;
        array[2] = third;

        ElementsPerThread<String> list = new ElementsPerThread<>(array);

        // When and Then
        assertThat(list.first(),is(first));
        assertThat(list.removeFirst(),is(first));
        assertThat(list.removeFirst(),is(second));
        assertThat(list.isEmpty(),is(false));
        assertThat(list.removeFirst(),is(third));
        assertThat(list.isEmpty(),is(true));
    }

}
