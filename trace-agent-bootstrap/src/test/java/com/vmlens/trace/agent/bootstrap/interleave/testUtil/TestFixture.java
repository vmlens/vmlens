package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.MAIN_THREAD_INDEX;

public class TestFixture {

    public static TestData volatileReadAndWrite() {
        ResultTestBuilderForActualRun resultTestBuilderForActualRun = new ResultTestBuilderForActualRun();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();
        orderArraysBuilder.addAlternatingOrder(withInverse(read, write));


        FeatureTestMatcher matcher = new FeatureTestMatcher();
        matcher.both(read, write);
        matcher.runs(2);

        return new TestData(resultTestBuilderForActualRun.factoryList(),
                new AlternatingOrderContainer(orderArraysBuilder.build(), resultTestBuilderForActualRun.positions()), matcher);
    }

    public static TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> startThread() {
        ResultTestBuilderForActualRun resultTestBuilderForActualRun = new ResultTestBuilderForActualRun();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.startThread(FIRST_WORKER_THREAD_INDEX);
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        return resultTestBuilderForActualRun.factoryList();
    }

    private static AlternatingOrderElement withInverse(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left, right), new LeftBeforeRight(right, left));
    }

}
