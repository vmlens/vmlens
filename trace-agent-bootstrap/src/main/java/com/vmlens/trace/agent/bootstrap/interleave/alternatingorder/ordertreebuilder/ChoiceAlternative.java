package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeNoOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public class ChoiceAlternative implements EitherInChoice {

    private EitherInChoiceAlternative next;

    public EitherInChoice either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(orderAlternativeA,orderAlternativeB);
        next = temp;
        return temp;
    }

    public EitherInChoiceAlternative next() {
        return next;
    }



    public int getLength() {
        ChoiceElement firstLast = next();
        ChoiceElement first = next();
        int firstCount = 0;
        while(first != null)  {
            firstLast = first;
            firstCount++;
            first = first.getNext();
        }
        return firstCount;
    }

    public void fill(int length) {
        int myLength = 0;
        ChoiceElement secondLast = next();
        ChoiceElement second = next();
        while(second != null)  {
            secondLast = second;
            myLength++;
            second = second.getNext();
        }
        if( length == myLength ) {
            return;
        }
        if(secondLast == null) {
            next = new EitherInChoiceAlternative(new AlternativeNoOrder(true),new AlternativeNoOrder(false));
            secondLast = next;
            myLength++;
        }
        for(int i = myLength; i < length; i++  ) {
            secondLast.fill();
            secondLast = secondLast.getNext();
        }
    }


}
