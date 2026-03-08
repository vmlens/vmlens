package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.DependentOperationAndPositionOrContainer;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;

public class DependentOperationAndPosition<ELEMENT extends DependentOperation>
                implements DependentOperationAndPositionOrContainer<DependentOperationAndPosition<ELEMENT>> {

    private final Position position;
    private final ELEMENT element;

    public DependentOperationAndPosition(Position position, ELEMENT element) {
        this.position = position;
        this.element = element;
    }

    public Position position() {
        return position;
    }

    public ELEMENT element() {
        return element;
    }


    @Override
    public ListBuilderNode addToAlternatingOrder(DependentOperationAndPosition<ELEMENT> elementDependentOperationAndPosition,
                                                 BuildAlternatingOrderContext context,
                                                 ListBuilderNode listBuilderNode) {
        return element.addToAlternatingOrder(position,elementDependentOperationAndPosition,context, listBuilderNode);
    }

    @Override
    public int threadIndex() {
        return position().threadIndex();
    }
}
