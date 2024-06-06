package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockContainer;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.testfixture.FixtureBuilder;
import com.vmlens.trace.agent.bootstrap.testfixture.ResultTestBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.p;
import static com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition.ep;
import static com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock.db;
import static com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorEnterImpl.enter;
import static com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorExit.exit;
import static com.vmlens.trace.agent.bootstrap.testfixture.FixtureBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.testfixture.FixtureBuilder.MAIN_THREAD_INDEX;

public class TestFixture {



    public static TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> startThread() {
        ResultTestBuilder resultTestBuilderForActualRun = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForActualRun);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.startThread(FIRST_WORKER_THREAD_INDEX);
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        return resultTestBuilderForActualRun.factoryList();
    }

    public static Pair<TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>>,
            AlternatingOrderContainer> threadJoin() {
        ResultTestBuilder resultTestBuilderForActualRun = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForActualRun);
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

    public static Pair<BlockContainer, TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> monitor() {
        ResultTestBuilder resultTestBuilderForBlock = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForBlock);
        builder.beginThread(0);
        builder.monitorEnter(0);
        builder.monitorExit(0);
        builder.beginThread(1);
        builder.monitorEnter(0);
        builder.monitorExit(0);

        Monitor monitor = new Monitor(0);

        BlockContainer blockContainer = new BlockContainer();
        blockContainer.addDependent(monitor.key(), db(ep(enter(monitor), p(0, 0)), ep(exit(monitor), p(0, 1))));
        blockContainer.addDependent(monitor.key(), db(ep(enter(monitor), p(1, 0)), ep(exit(monitor), p(1, 1))));


        return new ImmutablePair<>(blockContainer, resultTestBuilderForBlock.blockBuilderList());
    }


    public static TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> chainedMonitor() {
        ResultTestBuilder resultTestBuilderForBlock = new ResultTestBuilder();
        FixtureBuilder builder = new FixtureBuilder(resultTestBuilderForBlock);
        builder.beginThread(0);
        builder.monitorEnter(0);
        builder.monitorEnter(0);
        builder.monitorExit(0);
        builder.monitorExit(0);
        return resultTestBuilderForBlock.blockBuilderList();
    }

    private static AlternatingOrderElement withInverse(Position left, Position right) {
        return new AlternatingOrderElement(new LeftBeforeRight(left, right), new LeftBeforeRight(right, left));
    }


}
