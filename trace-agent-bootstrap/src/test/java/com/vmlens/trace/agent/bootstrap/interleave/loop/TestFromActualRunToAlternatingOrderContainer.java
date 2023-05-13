package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainerJsonMemento;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ActualRunJsonMemento;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestFromActualRunToAlternatingOrderContainer {
    @Test
    public void volatileReadAndWrite() {
        // Given
        AlternatingOrderContainer expected = AlternatingOrderContainerJsonMemento.load("volatileReadAndWrite");
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun = ActualRunJsonMemento.load("volatileReadAndWrite");

        // When
        AlternatingOrderContainer alternatingOrderContainer = InterleaveLoop.create(actualRun);

        // Then
        alternatingOrderContainer.debug(new AgentLoggerForTest());
        assertThat(alternatingOrderContainer, is(expected));
    }
}
