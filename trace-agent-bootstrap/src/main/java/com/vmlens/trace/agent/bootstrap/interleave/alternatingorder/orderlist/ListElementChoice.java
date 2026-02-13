package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.ForEachCallback;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ChoiceAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.EitherInChoiceAlternative;

public class ListElementChoice implements ListElement {

    private final ListElementChoiceAlternative firstAlternative;
    private final ListElementChoiceAlternative secondAlternative;
    // next can be null
    private ListElement next;

    public ListElementChoice(ListElementChoiceAlternative firstAlternative, ListElementChoiceAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    public static ListElement create(ChoiceAlternative alternativeA, ChoiceAlternative alternativeB) {
        StartAndEnd first = build(alternativeA);
        StartAndEnd second = build(alternativeA);

        ListElementChoice listElementChoice = new ListElementChoice(first.start(),
                second.start());

        first.end().setChoice(listElementChoice);
        second.end().setChoice(listElementChoice);

        return listElementChoice;
    }

    private static StartAndEnd build(ChoiceAlternative alternative)  {
        EitherInChoiceAlternative current = alternative.next();
        ListElementChoiceAlternative previous = null;
        ListElementChoiceAlternative startElement =  null;

        while(current != null) {
            ListElementChoiceAlternative currentListElement = current.build();
            if(startElement == null) {
                startElement = currentListElement;
            }
            if(previous != null) {
                previous.setNext(currentListElement);
            }
            previous = currentListElement;
            current = current.getNext();
        }
        return new StartAndEnd(startElement,previous);
    }

    @Override
    public NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean firstAlternative) {
        if(firstAlternative) {
            return new NextNodeAndProcessFlag(this.firstAlternative,true);
        }
        return new NextNodeAndProcessFlag(this.secondAlternative,true);
    }

    // To test the builder
    public OrderListNode firstAlternative() {
        return firstAlternative;
    }

    // To test the builder
    public OrderListNode secondAlternative() {
        return secondAlternative;
    }

    @Override
    public OrderListNode nextLeft() {
        return firstAlternative;
    }

    @Override
    public boolean hasSameOrder(OrderListNode otherNode) {
        if(! (otherNode instanceof ListElementChoice)) {
            return false;
        }

        ListElementChoice other = (ListElementChoice) otherNode;

        if(firstAlternative.hasSameOrder(other.firstAlternative) && secondAlternative.hasSameOrder(other.secondAlternative)) {
            return true;
        }
        return firstAlternative.hasSameOrder(other.secondAlternative) && secondAlternative.hasSameOrder(other.firstAlternative);
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    public OrderListNode getNextForAlternative() {
        return next;
    }

    @Override
    public void foreach(ForEachCallback callback) {
        firstAlternative.foreach(callback);
        secondAlternative.foreach(callback);
        if(next != null) {
            next.foreach(callback);
        }
    }

    @Override
    public boolean removeBecauseOfCycle() {
        return false;
    }

    @Override
    public ListElement getNextListElement() {
        return next;
    }
}
