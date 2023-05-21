package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestData;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.TestFixture;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFromActualRunToAlternatingOrderContainer {
    @Test
    public void volatileReadAndWrite() {
        // Given
        TestData testData = TestFixture.volatileReadAndWrite();

        // When
        AlternatingOrderContainer alternatingOrderContainer = InterleaveLoop.create(testData.actualRun());

        // Then
        assertThat(alternatingOrderContainer, is(testData.alternatingOrderContainer()));
    }
}
