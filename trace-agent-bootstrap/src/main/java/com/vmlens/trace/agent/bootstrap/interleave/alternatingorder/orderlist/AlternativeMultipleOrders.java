package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternativeMultipleOrders implements OrderAlternative {

    private final TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives;

    public AlternativeMultipleOrders(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives) {
        this.combinedAlternatives = combinedAlternatives;
    }

    public static AlternativeMultipleOrders alternativeTwoOrders(LeftBeforeRight first,LeftBeforeRight second) {
        TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives  = new TLinkedList<>();
        combinedAlternatives.add(TLinkableWrapper.wrap(new AlternativeOneOrder(first)));
        combinedAlternatives.add(TLinkableWrapper.wrap(new AlternativeOneOrder(second)));
        return new AlternativeMultipleOrders(combinedAlternatives);
    }


    @Override
    public void process(CreateOrderContext context) {
      for(TLinkableWrapper<AlternativeOneOrder> element : combinedAlternatives) {
          element.element().process(context);
      }
    }

    @Override
    public void addToCombinedAlternatives(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> result) {
        for(TLinkableWrapper<AlternativeOneOrder> element : combinedAlternatives) {
            result.add(TLinkableWrapper.wrap(element.element()));
        }
    }


    @Override
    public boolean isPartOfCycle(OrderCycle orderCycle) {
        for(TLinkableWrapper<AlternativeOneOrder> element : combinedAlternatives) {
            if(orderCycle.first().equals(element.element().getLeftBeforeRight())) {
                return true;
            }
            if(orderCycle.second().equals(element.element().getLeftBeforeRight())) {
                return true;
            }
        }
        return false;
    }

    /**
     * used by CartesianProduct for removing cylces
     * if the list of OrderAlternative contains the cycle return null otherwise return
     * this
     *
     * @param orderCycle
     * @return
     */
    public boolean containsCycle(OrderCycle orderCycle) {
        orderCycle.reset();
        for(TLinkableWrapper<AlternativeOneOrder> element : combinedAlternatives) {
            if(orderCycle.first().equals(element.element().getLeftBeforeRight())) {
                orderCycle.activateFirst();
            }
        }
        for(TLinkableWrapper<AlternativeOneOrder> element : combinedAlternatives) {
            if(orderCycle.second().equals(element.element().getLeftBeforeRight())) {
                orderCycle.activateSecond();
            }
        }
        if(orderCycle.isActivated()) {
            return true;
        }
        return false;
    }

}
