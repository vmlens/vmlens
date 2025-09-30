package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Either;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;

import java.util.List;

import static com.vmlens.trace.agent.bootstrap.Either.left;
import static com.vmlens.trace.agent.bootstrap.Either.right;

public class CycleDetectionAdapter {

    private final CalculatedRunBuilder calculatedRunBuilder;

    public CycleDetectionAdapter(CalculatedRunBuilder calculatedRunBuilder) {
        this.calculatedRunBuilder = calculatedRunBuilder;
    }

    public Either<CalculatedRun,Cycle> build(OrderArrayList orderArrayList) {
        for(int i = 0 ; i <  orderArrayList.length(); i++ ) {
            calculatedRunBuilder.addOrder(orderArrayList.get(i));
        }

        List<List<Position>> cycleList = calculatedRunBuilder.buildCycles();

        if(cycleList.isEmpty()) {
            return left(calculatedRunBuilder.build());
        } else {
            return right(Cycle.create(cycleList,orderArrayList.alternatingOrderIterator()));
        }
    }


}
