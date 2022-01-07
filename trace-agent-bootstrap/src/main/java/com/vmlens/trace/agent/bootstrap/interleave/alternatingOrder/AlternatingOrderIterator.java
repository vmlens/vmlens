package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.domain.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class AlternatingOrderIterator {

    private final TLinkableWrapper<AlternatingOrderElement>[] optionalAlternatingOrderElements;
    private final PermutationIterator permutationIterator;

    public AlternatingOrderIterator(TLinkableWrapper<AlternatingOrderElement>[] optionalAlternatingOrderElements) {
        this.optionalAlternatingOrderElements = optionalAlternatingOrderElements;
        this.permutationIterator = new PermutationIterator(optionalAlternatingOrderElements.length);
    }

    LeftBeforeRight[] next() {
        LeftBeforeRight[] array = new LeftBeforeRight[optionalAlternatingOrderElements.length];
        for (int i = 0; i < optionalAlternatingOrderElements.length; i++) {
            array[i] = optionalAlternatingOrderElements[i].element.getOrder(permutationIterator.at(i));
        }
        permutationIterator.advance();
        return array;
    }

    boolean hasNext() {
        return permutationIterator.hasNext();
    }

}
