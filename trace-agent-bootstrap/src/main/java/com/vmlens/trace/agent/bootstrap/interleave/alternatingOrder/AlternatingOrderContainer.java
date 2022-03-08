package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.util.Arrays;

/**
 * Container for the fixed and alternating order elements.
 * Actual runs are compared using this class. Creates all CalculatedRuns for the order elements through an iterator.
 */


public class AlternatingOrderContainer {

    private final Logger logger;
    final TLinkableWrapper<AlternatingOrderElement>[] optionalAlternatingOrderElements;
    final  TLinkableWrapper<LeftBeforeRight>[] fixedOrders;
    final int[] length;

    static void sort(TLinkableWrapper<AlternatingOrderElement>[] unsorted) {
        Arrays.sort(unsorted, new AlternatingOrderElementComparator());
    }

    static void sortFixed(TLinkableWrapper<LeftBeforeRight>[] unsorted) {
        Arrays.sort(unsorted, new TLinkableWrapperLeftBeforeRightComparator());
    }

    public AlternatingOrderContainer(Logger logger,      FixedAndAlternatingOrder fixedAndAlternatingOrder,
                                     int[] length) {
        sortFixed(fixedAndAlternatingOrder.fixedOrder);
        sort(fixedAndAlternatingOrder.alternatingOrder);
        this.logger = logger;
        this.fixedOrders = fixedAndAlternatingOrder.fixedOrder;
        this.optionalAlternatingOrderElements = fixedAndAlternatingOrder.alternatingOrder;
        this.length = length;
        if (logger.isOrderEnabled()) {
            boolean isFirst = true;
            String result = "";
            for (TLinkableWrapper<LeftBeforeRight> element : fixedOrders) {
                if (!isFirst) {
                    result += ",\n";
                }
                isFirst = false;
                result += "f" + element.element.toString();
            }
            for (TLinkableWrapper<AlternatingOrderElement> element : optionalAlternatingOrderElements) {
                if (!isFirst) {
                    result += ",\n";
                }
                isFirst = false;
                result += element.element.toString();
            }
            logger.order(result);
        }
    }

    public AlternatingOrderContainerIterator iterator() {
        return new AlternatingOrderContainerIterator(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlternatingOrderContainer that = (AlternatingOrderContainer) o;

        if (!Arrays.equals(optionalAlternatingOrderElements, that.optionalAlternatingOrderElements)) return false;
       return Arrays.equals(fixedOrders, that.fixedOrders);
       
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(optionalAlternatingOrderElements);
        result = 31 * result + Arrays.hashCode(fixedOrders);
        return result;
    }
}
