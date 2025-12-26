package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.CreateOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.NextNodeAndProcessFlag;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

import java.util.Iterator;

public abstract class NodeWithCycles implements ForEachApply , OrderTreeNode {

    protected final OrderAlternative firstAlternative;
    protected final OrderAlternative secondAlternative;
    private ArrayList<CycleAdapter> cycleAdapterList;

    public NodeWithCycles(OrderAlternative firstAlternative,
                          OrderAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    @Override
    public NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean takeFirstAlternative) {
        boolean processFlag;

        if(cycleAdapterList != null) {
            Iterator<CycleAdapter> iter = cycleAdapterList.iterator();
            while(iter.hasNext()) {
                CycleAdapter adapter = iter.next();
                adapter.activate(takeFirstAlternative);
                if(adapter.isActivated()) {
                    return new NextNodeAndProcessFlag(nextForAddOrder(),false);
                }
            }
        }
        if(takeFirstAlternative) {
            processFlag = firstAlternative.process(context);
        } else {
            processFlag = secondAlternative.process(context);
        }
        return new NextNodeAndProcessFlag(nextForAddOrder(),processFlag);
    }


    public void avoidCycles(OrderCycle[] array) {
        for(OrderCycle cycle : array) {
            Boolean result = createsOrder(cycle.first());
            if(result != null) {
                addToList(new CycleAdapterForFirst(result,cycle));
            }
            result = createsOrder(cycle.second());
            if(result != null) {
                addToList(new CycleAdapterForSecond(result,cycle));
            }
        }
    }

    public Boolean createsOrder(LeftBeforeRight leftBeforeRight) {
        if(firstAlternative.createOrder(leftBeforeRight)) {
            return true;
        } else if(secondAlternative.createOrder(leftBeforeRight)) {
            return false;
        }
        return null;
    }

    @Override
    public void foreach(ForEachCallback callback) {
        callback.consume(this);
        if(nextForEach() != null) {
            nextForEach().foreach(callback);
        }
    }



    protected abstract ForEachApply nextForEach();
    protected abstract OrderTreeNode nextForAddOrder();


    private void addToList(CycleAdapter cycleAdapter) {
        if(cycleAdapterList == null) {
            cycleAdapterList = new ArrayList<>(cycleAdapter);
        } else {
            cycleAdapterList.add(cycleAdapter);
        }
    }


}
