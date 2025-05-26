package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class ListNodeBuilder extends EitherListOrStartNodeBuilder {

    private final ListNodeAlternative firstAlternative;
    private final ListNodeAlternative secondAlternative;


    public ListNodeBuilder(AlternativeTuple eitherAlternatives) {
        this.firstAlternative = eitherAlternatives.first();
        this.secondAlternative = eitherAlternatives.second();
    }

    public ListNodeBuilder(ListNodeAlternative firstAlternative,
                           ListNodeAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }


    @Override
    public OrderTreeNode build() {
        OrderTreeNode nextNode = null;
        if(next != null) {
            nextNode = next.build();
        }

        return new ListNode(nextNode, firstAlternative, secondAlternative);
    }
}
