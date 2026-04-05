package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.nottraced.util.MixedRadixIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.CalculatedRunFactory;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.OrderArrayListFactory;

import java.util.Iterator;

public class AlternatingOrderContainerIterator implements  Iterator<CalculatedRun> , AlternatingOrderContainerForCycleFound {

    private final AlternatingOrderContainer alternatingOrderContainer;
    private MixedRadixIterator mixedRadixIterator;
    private CalculatedRunFactory calculatedRunFactory;


    public AlternatingOrderContainerIterator(AlternatingOrderContainer alternatingOrderContainer) {
        this.alternatingOrderContainer = alternatingOrderContainer;
        this.mixedRadixIterator = new MixedRadixIterator(alternatingOrderContainer.orderList().numberOfAlternativeArray());
        this.calculatedRunFactory = new CalculatedRunFactory(new OrderArrayListFactory(
                alternatingOrderContainer.fixedOrderArray()),
                alternatingOrderContainer.actualRun(),
                alternatingOrderContainer.interleaveLoopContext());
    }

    @Override
    public boolean hasNext() {
        if(mixedRadixIterator == null) {
            return false;
        }

        boolean temp =  mixedRadixIterator.hasNext();
        if(!temp) {
            mixedRadixIterator = null;
            calculatedRunFactory = null;
            alternatingOrderContainer.setFieldsToNull();
        }
        return temp;
    }
    /**
     * can return null
     */
    @Override
    public CalculatedRun next() {
        int[] current = mixedRadixIterator.next();
        return calculatedRunFactory.create(current,orderList(),new CycleFoundCallbackImpl(this));
    }

    public int numberOfAlternatives() {
        return mixedRadixIterator.numberOfAlternatives();
    }

    @Override
    public OrderList orderList() {
        return alternatingOrderContainer.orderList();
    }

    @Override
    public void resetOrderList(OrderList orderList) {
        mixedRadixIterator = new MixedRadixIterator(orderList.numberOfAlternativeArray());
        alternatingOrderContainer.resetOrderList(orderList);
    }

}
