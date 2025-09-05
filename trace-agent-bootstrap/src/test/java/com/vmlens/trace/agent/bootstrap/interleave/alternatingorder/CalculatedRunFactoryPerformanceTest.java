package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeIterator;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.CalculatedRunFactory;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class CalculatedRunFactoryPerformanceTest {

    public void create() {
        // Given
        LeftBeforeRight[] order = new  LeftBeforeRight[5];
        order[0] = LeftBeforeRight.lbr(0,1, 1, 2);
        order[1] = LeftBeforeRight.lbr(1,3, 0, 3);
        order[2] = LeftBeforeRight.lbr(0,4, 1, 5);
        order[3] = LeftBeforeRight.lbr(1,5, 2, 3);
        order[4] = LeftBeforeRight.lbr(1,3, 2, 5);
        OrderTreeIterator orderTreeIterator = new OrderTreeIteratorForTest(order);

        PermutationIterator permutationIterator = new PermutationIterator(5);

        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        for(int index = 0; index < 4; index++) {
            for(int p = 0; p < 6; p++) {
                actualRun.add(pos(index,p));
            }
        }

        // When
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < 1000000; i++) {
            new CalculatedRunFactory(new LeftBeforeRight[0],actualRun).create(orderTreeIterator, permutationIterator );
        }
        System.out.println("took:" + (System.currentTimeMillis() -start));
        // 1      13
        // 100    64
        // 1000000   2756, 2206
    }

}
