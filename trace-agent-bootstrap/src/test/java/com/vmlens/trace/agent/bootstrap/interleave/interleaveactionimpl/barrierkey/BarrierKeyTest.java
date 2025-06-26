package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BarrierKeyTest {

    @Test
    public void equalsNormalizedDifferentKeyTypes() {
      // Given
        NormalizeContext normalizeContext = new NormalizeContext();
        FutureKey futureKey = new FutureKey(1L);
        PhaserKey phaserKey = new PhaserKey(1L);

      // When
      boolean equals = futureKey.equalsNormalized(normalizeContext,phaserKey);

      // Then
      assertThat(equals,is(false));
    }

    @Test
    public void equalsNormalizedSameKey() {
        // Given
        NormalizeContext normalizeContext = new NormalizeContext();
        FutureKey futureKeyA = new FutureKey(1L);
        FutureKey futureKeyB = new FutureKey(1L);

        // When
        boolean equals = futureKeyA.equalsNormalized(normalizeContext,futureKeyB);

        // Then
        assertThat(equals,is(true));
    }

}
