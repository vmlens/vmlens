package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeMultipleOrders;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CartesianProduct {

    public TLinkedList<TLinkableWrapper<AlternativeMultipleOrders>> cartesianProduct(TLinkedList<TLinkableWrapper<OrderListElement>> lists) {
        TLinkedList<TLinkableWrapper<AlternativeMultipleOrders>> result = new TLinkedList<>();
        int[] array = new int[lists.size()];
        for(int i = 0; i < lists.size(); i++) {
            array[i] = lists.get(i).element().numberOfAlternatives();
        }

        MixedRadixIterator iterator = new MixedRadixIterator(array);
        while(iterator.hasNext()) {
            TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives  = new TLinkedList<>();
            result.add(TLinkableWrapper.wrap(new AlternativeMultipleOrders(combinedAlternatives)));
            int[] current = iterator.next();
            for(int i = 0; i < array.length; i++) {
                lists.get(i).element().addToCombinedAlternatives(combinedAlternatives,current[i]);
            }
        }
        return result;
    }

}