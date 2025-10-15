package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.*;

import static com.vmlens.trace.agent.bootstrap.TraceFlags.TRACE_INTERLEAVE_INT_TEST_PERFORMANCE;
import static com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop.create;
import static com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop.createAlternatingOrderContainer;

public class IntTestRunner {

    public List<Position[]> runTest(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun,
                                         Expected expected) {
        return runTest(actualRun,expected,new InterleaveLoopContextBuilder().build(new QueueInMock(),0));
    }

    public List<Position[]> runTest(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun,
                                    Expected expected,
                                    InterleaveLoopContext interleaveLoopContext) {
        Map<ExpectedElement,Integer> fulfilled = new HashMap<>();

        AlternatingOrderContainer alternatingOrder = createAlternatingOrderContainer(create(actualRun), interleaveLoopContext);

        List<Position[]> executed = new LinkedList<>();
        Iterator<CalculatedRun> iter = alternatingOrder.iterator();
        int count = 0;
        long wasNull=0;
        while (iter.hasNext()) {
            CalculatedRun calculatedRun = iter.next();
            if (calculatedRun != null) {
                if(TRACE_INTERLEAVE_INT_TEST_PERFORMANCE) {
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
