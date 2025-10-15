package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.TwoChildrenNode;

import static java.lang.Math.max;

public class Choice implements NodeBuilder {

    private final ChoiceAlternative alternativeA =  new ChoiceAlternative();
    private final ChoiceAlternative alternativeB =  new ChoiceAlternative();
    private StartOrNext next;

    public ChoiceAlternative alternativeA() {
        return alternativeA;
    }

    public ChoiceAlternative alternativeB() {
        return alternativeB;
    }

    public StartOrNext next() {
        next = new StartOrNext();
        return next;
    }

    /**
     * build process currently works only for one choice
     */

    @Override
    public OrderTreeNode build(OrderTreeBuilderContext orderTreeBuilderContext) {
        // First fill both alternatives so that they have the same size
        int length = max(alternativeA.getLength(),alternativeB.getLength());
        alternativeA.fill(length);
        alternativeB.fill(length);

        // Set next as last element for both alternatives
        alternativeA.setLast(next);
        alternativeB.setLast(next);

        return new TwoChildrenNode(alternativeA.build(orderTreeBuilderContext),alternativeB.build(orderTreeBuilderContext));
    }

    @Override
    public NodeBuilder getNext() {
        return next;
    }

}
