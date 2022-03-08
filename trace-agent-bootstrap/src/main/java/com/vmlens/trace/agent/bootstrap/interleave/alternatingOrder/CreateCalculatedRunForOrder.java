package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.l;

/**
 * One array per thread. The right part of the left before right order is stored in the array. current length per array.
 */


public class CreateCalculatedRunForOrder {


    private final int[] length;
    private final TLinkedList<TLinkableWrapper<LeftBeforeRight>> order;

    public CreateCalculatedRunForOrder(TLinkedList<TLinkableWrapper<LeftBeforeRight>> order, int[] length) {
        this.length = length.clone();
        this.order = order;
    }

    private void take(Position position) {
        length[position.threadIndex]--;
        Iterator<TLinkableWrapper<LeftBeforeRight>> iterator = order.iterator();
        while (iterator.hasNext()) {
            TLinkableWrapper<LeftBeforeRight> current = iterator.next();
            if(current.element.right.equals(position) ) {
                iterator.remove();
            }
        }
        
    }

    private boolean isFree(Position position) {
        for(TLinkableWrapper<LeftBeforeRight> leftBeforeRight : order) {
            if( position.threadIndex == leftBeforeRight.element.left.threadIndex &&
                    position.positionInThread <= leftBeforeRight.element.left.positionInThread) {
                return false;
            }
        }
        return true;
    }

    public TLinkedList<TLinkableWrapper<Position>> create() {
        TLinkedList<TLinkableWrapper<Position>> result = new TLinkedList<TLinkableWrapper<Position>>();
        while (true) {
            Position nextElement = null;
            boolean elementsStillThere = false;
            for (int i = 0; i < length.length; i++) {
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

    private Position getElementForIndex(int i) {
        if( length[i] == 0  ) {
            return null;
        }
        return new Position(i, length[i] -1);
        
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
