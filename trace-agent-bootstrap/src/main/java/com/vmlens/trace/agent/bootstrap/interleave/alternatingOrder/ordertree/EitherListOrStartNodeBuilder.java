package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public abstract class EitherListOrStartNodeBuilder implements NodeBuilder {

    protected NodeBuilder next;

    public ListNodeBuilder listNode(ListNodeAlternative firstAlternative,
                                        ListNodeAlternative secondAlternative) {
        ListNodeBuilder builder = new ListNodeBuilder(firstAlternative, secondAlternative);
        this.next =  builder;
        return builder;
    }

    public EitherNodeBuilder either(AlternativeTuple first, AlternativeTuple second )  {
        EitherNodeBuilder builder = new EitherNodeBuilder(new ListNodeBuilder(first),
                new ListNodeBuilder(second));
        this.next =  builder;
        return builder;
    }

}
