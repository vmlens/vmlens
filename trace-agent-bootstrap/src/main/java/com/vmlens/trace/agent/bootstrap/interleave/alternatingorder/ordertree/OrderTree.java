package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;


import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.ArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.ForEachCallback;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.OrderCycle;

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
public class OrderTree {

    // can be null, when only fixed orders exist
    private final ListElement start;
    private final int length;
    private ArrayList<OrderCycle>  orderCycleList = null;

    public OrderTree(ListElement start) {
        this.start = start;
        this.length = calculateLength(start);
    }

    private static int calculateLength(OrderTreeNode start) {
        int size = 0;
        OrderTreeNode current = start;
        while(current != null) {
            size++;
            current = current.nextLeft();
        }
        return size;
    }

    public OrderTreeIterator createIteratorAndResetOrderCycles() {
        if(orderCycleList != null) {
            Iterator<OrderCycle> iter = orderCycleList.iterator();
            while(iter.hasNext()) {
                iter.next().reset();
            }
        }
        return new OrderTreeIteratorImpl(start);
    }

    public int length() {
        return length;
    }

    // To test the builder
    public OrderTreeNode start() {
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

}
