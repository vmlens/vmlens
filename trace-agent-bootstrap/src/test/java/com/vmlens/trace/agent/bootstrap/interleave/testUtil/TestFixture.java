package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;

import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.MAIN_THREAD_INDEX;

public class TestFixture {

    public static Triple<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>,
            AlternatingOrderContainer, FeatureTestMatcher> volatileReadAndWrite() {
        ResultTestBuilderForActualRun resultTestBuilderForActualRun = new ResultTestBuilderForActualRun();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();
        orderArraysBuilder.addAlternatingOrder(withInverse(read, write));


        FeatureTestMatcherBuilder featureTestMatcherBuilder = new FeatureTestMatcherBuilder();
        featureTestMatcherBuilder.both(read, write);
        featureTestMatcherBuilder.runs(2);

        FeatureTestMatcher matcher = featureTestMatcherBuilder.build();

        return new ImmutableTriple<>(resultTestBuilderForActualRun.factoryList(),
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

    public static Pair<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>,
            AlternatingOrderContainer> threadJoin() {
        ResultTestBuilderForActualRun resultTestBuilderForActualRun = new ResultTestBuilderForActualRun();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position start = builder.startThread(FIRST_WORKER_THREAD_INDEX);
        Position join = builder.joinThread(FIRST_WORKER_THREAD_INDEX);
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();

        Position end = new Position(read.threadIndex, read.positionInThread + 1);

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();
        orderArraysBuilder.addFixedOrder(lbr(start, read));
        orderArraysBuilder.addFixedOrder(lbr(end, join));

        return new ImmutablePair<>(resultTestBuilderForActualRun.factoryList(),
                new AlternatingOrderContainer(orderArraysBuilder.build(), resultTestBuilderForActualRun.positions()));
    }

    private static AlternatingOrderElement withInverse(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left, right), new LeftBeforeRight(right, left));
    }

    private static LeftBeforeRight lbr(Position left, Position right) {
        return new LeftBeforeRight(left, right);
    }

}
