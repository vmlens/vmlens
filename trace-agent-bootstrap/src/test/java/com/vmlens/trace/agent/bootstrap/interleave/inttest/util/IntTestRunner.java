package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.*;

public class IntTestRunner {

    public static final boolean TRACE = false;

    public List<Position[]> runTest(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun,
                                         Expected expected) {
        return runTest(actualRun,expected,new InterleaveLoopContextBuilder().build(new QueueInMock(),0));
    }

    public List<Position[]> runTest(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun,
                                    Expected expected,
                                    InterleaveLoopContext interleaveLoopContext) {
        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();
        Map<ExpectedElement,Integer> fulfilled = new HashMap<>();
        AlternatingOrderContainer alternatingOrder = factory.create(actualRun, interleaveLoopContext);

        List<Position[]> executed = new LinkedList<>();
        Iterator<CalculatedRun> iter = alternatingOrder.iterator();
        int count = 0;
        long wasNull=0;
        while (iter.hasNext()) {
            CalculatedRun calculatedRun = iter.next();
            if (calculatedRun != null) {
                if(TRACE) {
                    System.out.println(wasNull);
                }
                wasNull = 0L;
                count++;
                if(count > interleaveLoopContext.maximumIterations()) {
                    break;
                }
                Potential potential = expected.asPotential();
                Position[] positions = calculatedRun.calculatedRunElementArray();
                executed.add(positions);
                for(Position position : positions) {
                    potential.next(position,fulfilled);
                }
            }
            else {
                wasNull++;
            }
        }
        expected.check(fulfilled,executed);
        return executed;
    }
}
