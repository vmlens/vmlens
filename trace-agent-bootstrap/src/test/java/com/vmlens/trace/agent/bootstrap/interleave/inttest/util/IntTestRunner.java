package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class IntTestRunner {

    public List<Position[]> runTest(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun, Expected expected) {
        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();
        Map<ExpectedElement,Integer> fulfilled = new HashMap<>();
        AlternatingOrderContainer alternatingOrder = factory.create(actualRun);

        List<Position[]> executed = new LinkedList<>();
        Iterator<CalculatedRun> iter = alternatingOrder.iterator();
        while (iter.hasNext()) {
            CalculatedRun calculatedRun = iter.next();
            if (calculatedRun != null) {
                Potential potential = expected.asPotential();
                Position[] positions = calculatedRun.calculatedRunElementArray();
                executed.add(positions);
                for(Position position : positions) {
                    potential.next(position,fulfilled);
                }
            }
        }
        expected.check(fulfilled,executed);
        return executed;
    }
}
