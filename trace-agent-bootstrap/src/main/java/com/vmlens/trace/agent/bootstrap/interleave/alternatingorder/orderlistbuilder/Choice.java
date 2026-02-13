package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.ListElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.ListElementChoice;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static java.lang.Math.max;

public class Choice extends StartOrEither implements NodeBuilder  {

    private final ChoiceAlternative alternativeA =  new ChoiceAlternative();
    private final ChoiceAlternative alternativeB =  new ChoiceAlternative();

    public Choice(TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList) {
        super(nodeBuilderList);
    }

    public ChoiceAlternative alternativeA() {
        return alternativeA;
    }
    public ChoiceAlternative alternativeB() {
        return alternativeB;
    }

    @Override
    public ListElement build() {
        // First fill both alternatives so that they have the same size
        int length = max(alternativeA.getLength(),alternativeB.getLength());
        alternativeA.fill(length);
        alternativeB.fill(length);

        return  ListElementChoice.create(alternativeA,alternativeB);
    }

}
