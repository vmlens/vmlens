package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.ListElementChoice;

import static java.lang.Math.max;

public class Choice extends StartOrEither implements NodeBuilder  {

    private final ChoiceAlternative alternativeA =  new ChoiceAlternative();
    private final ChoiceAlternative alternativeB =  new ChoiceAlternative();


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

    public TreeBuilderNode next() {
        return this;
    }
}
