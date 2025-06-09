package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.TwoChildrenNode;

public class Choice implements NodeBuilder {

    private ChoiceAlternative alternativeA;
    private ChoiceAlternative alternativeB;
    private StartOrNext next;

    public ChoiceAlternative alternativeA() {
        alternativeA = new ChoiceAlternative();
        return alternativeA;
    }

    public ChoiceAlternative alternativeB() {
        alternativeB = new ChoiceAlternative();
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
    public OrderTreeNode build() {
        // First fill both alternatives so that they have the same size
        ChoiceElement firstLast = alternativeA.next();
        ChoiceElement first = alternativeA.next();
        int firstCount = 0;
        while(first != null)  {
            firstLast = first;
            firstCount++;
            first = first.getNext();
        }

        ChoiceElement secondLast = alternativeB.next();
        ChoiceElement second = alternativeB.next();
        int secondCount = 0;
        while(second != null)  {
            secondLast = second;
            secondCount++;
            second = second.getNext();
        }

        for( int i = firstCount; i < secondCount;i++ ) {
            firstLast.fill();
            firstLast = firstLast.getNext();
        }

        for( int i = secondCount; i < firstCount;i++ ) {
            secondLast.fill();
            secondLast = secondLast.getNext();
        }


        // Set next as last element for both alternatives
        firstLast.setLast(next);
        secondLast.setLast(next);

        return new TwoChildrenNode(alternativeA.build(),alternativeB.build());
    }

    @Override
    public NodeBuilder getNext() {
        return next;
    }
}
