package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeMultipleOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.CycleFoundCallback;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;
import java.util.Map;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class CycleFoundCallbackImpl implements CycleFoundCallback {

    private final AlternatingOrderContainerForCycleFound alternatingOrderContainerIterator;

    public CycleFoundCallbackImpl(AlternatingOrderContainerForCycleFound alternatingOrderContainerIterator) {
        this.alternatingOrderContainerIterator = alternatingOrderContainerIterator;
    }


    /**
     * if one of the n alternatives of the OrderListElement are part of the cycle
     * we create a new ElementInCycle and add it to the list
     * <p>
     * in principal it could happen that an element is part of two cycles
     * This should work since we remove the cycle in the next run
     * <p>
     * we create all combinations, and remove those alternatives which contain a
     * cycle
     * <p>
     * OrderListElement -> n alternatives of OrderAlternative
     *
     * @param array
     */

    public void found(OrderCycle[] array) {
        THashMap<OrderCycle, TLinkedList<TLinkableWrapper<OrderListElement>>> elementsInCycleMap = new THashMap<>();
        TLinkedList<TLinkableWrapper<OrderListElement>> result = new TLinkedList<>();

        Iterator<TLinkableWrapper<OrderListElement>> iter =
                alternatingOrderContainerIterator.orderList().iterator();
        while (iter.hasNext()) {
            OrderListElement current = iter.next().element();
            add(current, array, elementsInCycleMap, result);
        }
        boolean cycleFound = false;
        TLinkedList<TLinkableWrapper<OrderAlternative>> cycleWithFixedElement = new TLinkedList<>();
        for (Map.Entry<OrderCycle, TLinkedList<TLinkableWrapper<OrderListElement>>> entry : elementsInCycleMap.entrySet()) {
            TLinkedList<TLinkableWrapper<AlternativeMultipleOrders>> cartesianProduct = new CartesianProduct().cartesianProduct(entry.getValue());
            TLinkedList<TLinkableWrapper<OrderAlternative>> forOnListElement = new TLinkedList<>();
            for (TLinkableWrapper<AlternativeMultipleOrders> element : cartesianProduct) {
                if (!element.element().containsCycle(entry.getKey())) {
                    forOnListElement.add(wrap(element.element()));
                } else {
                    cycleFound = true;
                }
            }
            // When the cycle contains a fixed order we do not find it here so we need to remove
            if (cycleFound) {
                result.add(wrap(new OrderListElement(forOnListElement)));
            } else {
                for(TLinkableWrapper<OrderAlternative> element : forOnListElement) {
                    if(! element.element().isPartOfCycle(entry.getKey())) {
                        cycleWithFixedElement.add(wrap(element.element()));
                    }

                }
            }
        }
        if(!cycleWithFixedElement.isEmpty()) {
            result.add(wrap(new OrderListElement(cycleWithFixedElement)));
        }
        alternatingOrderContainerIterator.resetOrderList(new OrderList(result));
    }

    private void add(OrderListElement current,
                     OrderCycle[] array,
                     THashMap<OrderCycle, TLinkedList<TLinkableWrapper<OrderListElement>>> elementsInCycleMap,
                     TLinkedList<TLinkableWrapper<OrderListElement>> notInCycle) {
        for (OrderCycle orderCycle : array) {
            if (current.isPartOfCycle(orderCycle)) {
                TLinkedList<TLinkableWrapper<OrderListElement>> elementsInCycle = elementsInCycleMap.get(orderCycle);
                if (elementsInCycle == null) {
                    elementsInCycle = new TLinkedList<>();
                    elementsInCycleMap.put(orderCycle, elementsInCycle);
                }
                elementsInCycle.add(wrap(current));
                return;
            }
        }
        notInCycle.add(wrap(current));
    }

}
