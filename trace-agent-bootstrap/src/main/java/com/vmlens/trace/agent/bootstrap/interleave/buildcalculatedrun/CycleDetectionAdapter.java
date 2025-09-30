package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Either;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import gnu.trove.set.hash.THashSet;

import static com.vmlens.trace.agent.bootstrap.Either.left;
import static com.vmlens.trace.agent.bootstrap.Either.right;

public class CycleDetectionAdapter {

    private final CalculatedRunBuilder calculatedRunBuilder;

    public CycleDetectionAdapter(CalculatedRunBuilder calculatedRunBuilder) {
        this.calculatedRunBuilder = calculatedRunBuilder;
    }

    public Either<CalculatedRun, THashSet<Position>> build(OrderArrayList orderArrayList,
                                                           THashSet<Position> previousStartingPoints) {
        for(int i = 0 ; i <  orderArrayList.length(); i++ ) {
            calculatedRunBuilder.addOrder(orderArrayList.get(i));
        }
        THashSet<Position> newStartingPoint = calculatedRunBuilder.buildCycles(previousStartingPoints);
        if(newStartingPoint.isEmpty()) {
            CalculatedRun run = calculatedRunBuilder.build();
            if(run != null) {
                return left(run);
            }
            return right(calculatedRunBuilder.buildAllCycles());
        } else {
            return right(newStartingPoint);
        }
    }

}
