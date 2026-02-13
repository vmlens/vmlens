package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;


import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.ArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.ForEachCallback;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;

import java.util.Iterator;

/**
 *  the order tree contains of a list of either single elements and two children elements
 *  so iterating consists of going through the list of elements
 *  when we reach a two children element the element takes over
 *
 *  we have two ways to iterate over:
 *      OrderTreeIterator iterator() takes only one path based on permutation iterator
 *      foreach takes all paths used for cycle adapter
 *
 */
public class OrderList {

    // can be null, when only fixed orders exist
    private final ListElement start;
    private final int length;
    private ArrayList<OrderCycle>  orderCycleList = null;

    public OrderList(ListElement start) {
        this.start = start;
        this.length = calculateLength(start);
    }

    private static int calculateLength(OrderListNode start) {
        int size = 0;
        OrderListNode current = start;
        while(current != null) {
            size++;
            current = current.nextLeft();
        }
        return size;
    }

    public OrderListIterator createIteratorAndResetOrderCycles() {
        if(orderCycleList != null) {
            Iterator<OrderCycle> iter = orderCycleList.iterator();
            while(iter.hasNext()) {
                iter.next().reset();
            }
        }
        return new OrderListIteratorImpl(start);
    }

    public int length() {
        return length;
    }

    // To test the builder
    public OrderListNode start() {
        return start;
    }


    public void avoidCycles(OrderCycle[] array) {
        if(orderCycleList == null) {
            orderCycleList = new ArrayList<>(array);
        } else {
            orderCycleList.addAll(array);
        }
        start.foreach(new ForEachCallback(array));
    }

    public OrderList removeCycles() {
        ListElement newStart = null;
        ListElement previous = null;
        ListElement current = start;
        while(current != null) {
            if( ! current.removeBecauseOfCycle()) {
                if(newStart == null) {
                    newStart = current;
                }
                if(previous!= null) {
                    previous.setNext(current);
                }
                previous = current;
            }
            current = current.getNextListElement();
        }
        previous.setNext(null);
        return new OrderList(newStart);
    }

}
