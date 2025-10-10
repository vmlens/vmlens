package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.NoOpInterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop.create;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class DetectAndReplaceIntTest {

    @Test
    public void waitWithTimeoutInWhileLoop() {
        // Given
        TLinkedList<TLinkableWrapper<InterleaveAction>> orig = new WaitWithTimeoutInWhileLoop().build();

        // When
        TLinkedList<TLinkableWrapper<InterleaveAction>> replaced = new DetectAndReplacePattern().replace(create(orig));

        // Then
        int newCount = 0;
        for(TLinkableWrapper<InterleaveAction> element : replaced) {
            if( ! ( element.element() instanceof NoOpInterleaveAction)) {
                newCount++;
            }
        }
        assertThat(newCount , lessThan(orig.size()));
    }

}
