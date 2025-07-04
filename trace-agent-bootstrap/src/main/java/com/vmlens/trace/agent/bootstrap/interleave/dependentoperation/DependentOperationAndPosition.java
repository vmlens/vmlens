package com.vmlens.trace.agent.bootstrap.interleave.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.DependentOperationAndPositionOrContainer;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;

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
    public TreeBuilderNode addToAlternatingOrder(DependentOperationAndPosition<ELEMENT> elementDependentOperationAndPosition,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        return element.addToAlternatingOrder(position,elementDependentOperationAndPosition,context,treeBuilderNode);
    }

    @Override
    public int threadIndex() {
        return position().threadIndex();
    }
}
