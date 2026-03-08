package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ChoiceAlternative  implements ChoiceEither{

    private final TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> eitherList;

    public ChoiceAlternative(TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> eitherList) {
        this.eitherList = eitherList;
    }

    public ChoiceEither either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(eitherList,orderAlternativeA,orderAlternativeB);
        eitherList.add(TLinkableWrapper.wrap(temp));
        return temp;
    }

    public int size() {
        return eitherList.size();
    }
}
