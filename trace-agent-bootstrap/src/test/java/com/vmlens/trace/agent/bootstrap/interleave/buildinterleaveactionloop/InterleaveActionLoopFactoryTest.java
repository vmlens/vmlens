package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveActionLoop;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.InterleaveActionGuineaPig.action;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveActionLoopFactoryTest {

    @Test
    public void givenTwoElementsLoopOneElement() {
        // Given
        TLinkedList<TLinkableWrapper<InterleaveAction>> list =
                new TLinkedList<>();
        list.add(wrap(action(0,"g")));
        list.add(wrap(action(0,"h")));

        for(int i = 0; i < 5; i++) {
            list.add(wrap(action(0,"a")));
            list.add(wrap(action(0,"b")));
            list.add(wrap(action(0,"c")));
        }

        list.add(wrap(action(0,"i")));
        list.add(wrap(action(0,"j")));

        // When
        TLinkedList<TLinkableWrapper<InterleaveAction>> result =
                new InterleaveActionLoopFactory().create(list);

        // Then
        assertThat(result.size(),is(8));
        assertThat(result.get(2).element(),instanceOf(InterleaveActionLoop.class));
        assertThat(result.get(3).element(),is(action(0,"a")));
    }

    @Test
    public void givenSingleElementLoop() {
        // Given
        TLinkedList<TLinkableWrapper<InterleaveAction>> list =
                new TLinkedList<>();
        list.add(wrap(action(0,"g")));

        for(int i = 0; i < 5; i++) {
            list.add(wrap(action(0,"a")));
        }
        list.add(wrap(action(0,"j")));

        // When
        TLinkedList<TLinkableWrapper<InterleaveAction>> result =
                new InterleaveActionLoopFactory().create(list);

        // Then
        assertThat(result.size(),is(4));
        assertThat(result.get(1).element(),instanceOf(InterleaveActionLoop.class));
        assertThat(result.get(2).element(),is(action(0,"a")));
    }

    @Test
    public void givenMultipleReadWriteRead() {
        // Given
        TLinkedList<TLinkableWrapper<InterleaveAction>> list =
                new TLinkedList<>();
        for(int i = 0; i < 5; i++) {
            list.add(wrap(action(0,"read")));
        }
        list.add(wrap(action(1,"write")));
        list.add(wrap(action(0,"read")));

        // When
        TLinkedList<TLinkableWrapper<InterleaveAction>> result =
                new InterleaveActionLoopFactory().create(list);

        // Then
        assertThat(result.size(),is(3));
        assertThat(result.get(0).element(),instanceOf(InterleaveActionLoop.class));
        assertThat(result.get(1).element(),is(action(1,"write")));
    }

}
