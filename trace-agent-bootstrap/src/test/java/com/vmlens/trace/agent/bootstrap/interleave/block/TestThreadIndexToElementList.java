package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
public class TestThreadIndexToElementList {

    @Test
    public void testAddAndRemove() {
        Position firstThreadOne = new Position(0, 0);
        Position secondThreadOne = new Position(0, 1);
        Position firstThreadTwo = new Position(1, 0);
        Position secondThreadTwo = new Position(1, 1);

        ThreadIndexToElementList<Position> threadIndexToElementList = new ThreadIndexToElementList<Position>();
        threadIndexToElementList.add(firstThreadTwo);
        threadIndexToElementList.add(firstThreadOne);
        threadIndexToElementList.add(secondThreadTwo);
        threadIndexToElementList.add(secondThreadOne);

        assertThat(threadIndexToElementList.maxThreadIndex(), is(1));
        assertThat(threadIndexToElementList.getAndRemoveAtIndex(0), is(firstThreadOne));
        assertThat(threadIndexToElementList.getAndRemoveAtIndex(0), is(secondThreadOne));
    }

    @Test
    public void testClone() {
        Position firstThreadOne = new Position(0, 0);
        Position secondThreadOne = new Position(0, 1);
        Position firstThreadTwo = new Position(1, 0);
        Position secondThreadTwo = new Position(1, 1);

        ThreadIndexToElementList<Position> threadIndexToElementList = new ThreadIndexToElementList<Position>();
        threadIndexToElementList.add(firstThreadTwo);
        threadIndexToElementList.add(firstThreadOne);
        threadIndexToElementList.add(secondThreadTwo);
        threadIndexToElementList.add(secondThreadOne);

        ThreadIndexToElementList<Position> clone =
                threadIndexToElementList.safeClone();
        assertThat(clone.maxThreadIndex(), is(1));
        Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<Position>>>> allThreads
                = clone.iterator();
        Iterator<TLinkableWrapper<Position>> threadIterator = allThreads.next().element.iterator();
        assertThat(threadIterator.next().element, is(firstThreadOne));
        assertThat(threadIterator.next().element, is(secondThreadOne));

    }
}
