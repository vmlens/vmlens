package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static java.lang.Math.max;

public class Choice extends StartOrEither implements NodeBuilder  {

    private final TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> alternativeA =  new TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> alternativeB =  new TLinkedList<>();

    public Choice(TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList) {
        super(nodeBuilderList);
    }

    public ChoiceAlternative alternativeA() {
        return new ChoiceAlternative(alternativeA);
    }
    public ChoiceAlternative alternativeB() {
        return new ChoiceAlternative(alternativeB);
    }

    @Override
    public OrderListElement build() {
        TLinkedList<TLinkableWrapper<OrderAlternative>> alternatives = new TLinkedList<>();
        processAlternative(alternativeA,alternatives);
        processAlternative(alternativeB,alternatives);

        return new OrderListElement(alternatives);
    }

    private static void processAlternative(TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> eitherInChoiceList,
                                           TLinkedList<TLinkableWrapper<OrderAlternative>> result) {
        PermutationIterator permutationIterator = new PermutationIterator(eitherInChoiceList.size());
        while (permutationIterator.hasNext()) {
            TLinkedList<TLinkableWrapper<OrderAlternative>> combinedAlternatives = new TLinkedList<>();
            result.add(TLinkableWrapper.wrap(new AlternativeMultipleOrders(combinedAlternatives)));
            Permutation permutation = permutationIterator.next();
            for(int i=0;i<eitherInChoiceList.size();i++) {
                combinedAlternatives.add(TLinkableWrapper.wrap(eitherInChoiceList.get(i).element().build(permutation.at(i))));
            }
        }
    }

}
