package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Objects;

public class AlternativeOneOrder implements OrderAlternative {

    private final LeftBeforeRight leftBeforeRight;

    public AlternativeOneOrder(LeftBeforeRight leftBeforeRight) {
        this.leftBeforeRight = leftBeforeRight;
    }

    public LeftBeforeRight getLeftBeforeRight() {
        return leftBeforeRight;
    }

    @Override
    public void process(CreateOrderContext context) {
        context.addOrder(leftBeforeRight);
    }

    @Override
    public void addToCombinedAlternatives(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives) {
        combinedAlternatives.add(TLinkableWrapper.wrap(this));
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        AlternativeOneOrder that = (AlternativeOneOrder) object;
        return Objects.equals(leftBeforeRight, that.leftBeforeRight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(leftBeforeRight);
    }

}
