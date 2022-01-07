package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.domain.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.l;

/**
 * One array per thread. The right part of the left before right order is stored in the array. current length per array.
 */


public class CreateCalculatedRunForOrder {

    private static class SortPerThread {
        int length;
        final LeftBeforeRight[] perThreadArray;

        public SortPerThread(int length) {
            this.length = length;
            perThreadArray = new LeftBeforeRight[length];
        }

        boolean hasRelation(Position position) {
            for (int i = 0; i < length; i++) {
                if (perThreadArray[i] != null) {
                    if (perThreadArray[i].left.threadIndex == position.threadIndex &&
                            position.positionInThread <= perThreadArray[i].left.positionInThread) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    private final SortPerThread[] array;
    private final int[] length;

    public CreateCalculatedRunForOrder(LeftBeforeRight[] order, int[] length) {
        this.length = length;
        array = new SortPerThread[length.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new SortPerThread(length[i]);
        }

        for (LeftBeforeRight current : order) {
            array[current.right.threadIndex].perThreadArray[current.right.positionInThread] = current;
        }

    }

    private Position getElementForIndex(int index) {
        SortPerThread current = array[index];
        if (current.length == 0) {
            return null;
        }
        if (current.perThreadArray[current.length - 1] != null) {
            return current.perThreadArray[current.length - 1].right;
        } else {
            return new Position(index, current.length - 1);
        }
    }

    private void take(Position position) {
        array[position.threadIndex].length--;
    }

    private boolean isFree(Position position) {
        for (int i = 0; i < array.length; i++) {
            if (i != position.threadIndex) {
                if (array[i].hasRelation(position)) {
                    return false;
                }
            }
        }
        return true;
    }

    public TLinkedList<TLinkableWrapper<Position>> create() {
        TLinkedList<TLinkableWrapper<Position>> result = new TLinkedList<TLinkableWrapper<Position>>();
        while (true) {
            Position nextElement = null;
            boolean elementsStillThere = false;
            for (int i = 0; i < array.length; i++) {
                Position current = getElementForIndex(i);
                if (current != null) {
                    elementsStillThere = true;
                    if (isFree(current)) {
                        take(current);
                        nextElement = current;
                        break;
                    }
                }
            }
            if (!elementsStillThere) {
                return reverse(result);
            }
            if (nextElement == null) {
                return null; // circle
            }
            result.add(l(nextElement));
        }
    }

    private TLinkedList<TLinkableWrapper<Position>> reverse(TLinkedList<TLinkableWrapper<Position>> list) {
        TLinkedList<TLinkableWrapper<Position>> reverse = new TLinkedList<TLinkableWrapper<Position>>();
        Iterator<TLinkableWrapper<Position>> iterator = list.iterator();
        while (iterator.hasNext()) {
            reverse.addFirst(l(iterator.next().element));
        }
        return reverse;
    }


}
