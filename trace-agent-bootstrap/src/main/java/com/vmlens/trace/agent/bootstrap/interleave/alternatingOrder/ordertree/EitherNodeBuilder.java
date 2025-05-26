package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class EitherNodeBuilder extends EitherListOrStartNodeBuilder {

    private final ListNodeBuilder firstAlternative;
    private final ListNodeBuilder secondAlternative;


    public EitherNodeBuilder(ListNodeBuilder firstAlternative, ListNodeBuilder secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    @Override
    public OrderTreeNode build() {
        // here we join the two alternatives again
        firstAlternative.next = next;
        secondAlternative.next = next;

        return new EitherNode(firstAlternative.build(),secondAlternative.build());
    }
}
