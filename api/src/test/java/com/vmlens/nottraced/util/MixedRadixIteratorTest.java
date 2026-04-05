package com.vmlens.nottraced.util;



import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;

public class MixedRadixIteratorTest {

    @Test
    public void shouldEnumerateAllCombinationsInCorrectOrder() {
        int[] bases = {2, 3};

        MixedRadixIterator iterator = new MixedRadixIterator(bases);

        List<int[]> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        List<int[]> expected = List.of(
                new int[]{0, 0},
                new int[]{0, 1},
                new int[]{0, 2},
                new int[]{1, 0},
                new int[]{1, 1},
                new int[]{1, 2}
        );

        assertThat(result.size(), is(expected.size()));

        for (int i = 0; i < expected.size(); i++) {
            assertThat(result.get(i), is(expected.get(i)));
        }
    }

    @Test
    public void shouldReturnFalseWhenFinished() {
        int[] bases = {1};

        MixedRadixIterator iterator = new MixedRadixIterator(bases);

        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }



    @Test
    public void shouldHandleSingleRadixOne() {
        int[] bases = {4};

        MixedRadixIterator iterator = new MixedRadixIterator(bases);

        List<int[]> result = new ArrayList<>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, contains(
                new int[]{0},
                new int[]{1},
                new int[]{2},
                new int[]{3}
        ));
    }
}
