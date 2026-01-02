package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.CalculatedRunFactory;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.OrderArrayListFactory;

import java.util.Iterator;

public class AlternatingOrderContainerIterator implements  Iterator<CalculatedRun>  {

    private final AlternatingOrderContainer alternatingOrderContainer;
    private PermutationIterator permutationIterator;
    private CalculatedRunFactory calculatedRunFactory;


    public AlternatingOrderContainerIterator(AlternatingOrderContainer alternatingOrderContainer) {
        this.alternatingOrderContainer = alternatingOrderContainer;
        this.permutationIterator = new PermutationIterator(alternatingOrderContainer.orderTree().length());
        this.calculatedRunFactory = new CalculatedRunFactory(new OrderArrayListFactory(
                alternatingOrderContainer.fixedOrderArray()),
                alternatingOrderContainer.actualRun(),
                alternatingOrderContainer.interleaveLoopContext(),
                alternatingOrderContainer.orderTree());
    }

    @Override
    public boolean hasNext() {
        if(permutationIterator == null) {
            return false;
        }

        boolean temp =  permutationIterator.hasNext();
        if(!temp) {
            permutationIterator = null;
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
        return calculatedRunFactory.create(permutationIterator);
    }

    public int length() {
        return calculatedRunFactory.length();
    }
}
