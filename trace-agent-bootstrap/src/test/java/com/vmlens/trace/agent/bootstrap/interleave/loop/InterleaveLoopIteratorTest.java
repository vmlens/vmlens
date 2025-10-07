package com.vmlens.trace.agent.bootstrap.interleave.loop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InterleaveLoopIteratorTest {

    @Test
    public void testEmptyQueue() {
        // Given
        IteratorQueue iteratorQueue = new IteratorQueueWrapper(new LinkedList<>());
        InterleaveLoopIterator iterator = new InterleaveLoopIterator(new InterleaveLoopContextBuilder().build(new QueueInMock(),0),iteratorQueue);

        // Then
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void testQueueWithOneElement() {
        // Given
        List<CalculatedRun> element = new LinkedList<>();
        CalculatedRun firstCalculatedRun = new CalculatedRun(new Position[0]);
        element.add(firstCalculatedRun);
        LinkedList<Iterator<CalculatedRun>> queue = new LinkedList<>();
        queue.add(element.iterator());


        IteratorQueue iteratorQueue = new IteratorQueueWrapper(queue);
        InterleaveLoopIterator iterator = new InterleaveLoopIterator(new InterleaveLoopContextBuilder().build(new QueueInMock(),0),iteratorQueue);

        // Then
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(firstCalculatedRun));
        assertThat(iterator.hasNext(), is(false));
    }

    @Ignore
    @Test
    public void testQueueWithTwoElementsAndNullValues() {
        // Given
        List<CalculatedRun> firstList = new LinkedList<>();
        CalculatedRun firstCalculatedRun = new CalculatedRun(new Position[0]);
        CalculatedRun secondCalculatedRun = new CalculatedRun(new Position[0]);
        firstList.add(null);
        firstList.add(firstCalculatedRun);
        firstList.add(null);
        firstList.add(null);
        firstList.add(secondCalculatedRun);

        List<CalculatedRun> secondList = new LinkedList<>();
        CalculatedRun thirdCalculatedRun = new CalculatedRun(new Position[0]);
        secondList.add(null);
        secondList.add(thirdCalculatedRun);


        LinkedList<Iterator<CalculatedRun>> queue = new LinkedList<>();
        queue.add(firstList.iterator());
        queue.add(secondList.iterator());

        IteratorQueue iteratorQueue = new IteratorQueueWrapper(queue);
        InterleaveLoopIterator iterator = new InterleaveLoopIterator(new InterleaveLoopContextBuilder().build(new QueueInMock(),0),iteratorQueue);

        // Then
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(firstCalculatedRun));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(secondCalculatedRun));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(thirdCalculatedRun));
        assertThat(iterator.hasNext(), is(false));
    }

    private class IteratorQueueWrapper implements IteratorQueue {
        private final LinkedList<Iterator<CalculatedRun>> queue;

        public IteratorQueueWrapper(LinkedList<Iterator<CalculatedRun>> queue) {
            this.queue = queue;
        }

        @Override
        public Iterator<CalculatedRun> poll() {
            return queue.poll();
        }
    }
}
